package com.acme.fromzeroapi.profiles.application.internal.queryServices;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.profiles.domain.model.valueObjects.ProfileId;
import com.acme.fromzeroapi.profiles.domain.services.ProfileQueryService;
import com.acme.fromzeroapi.profiles.domain.model.queries.*;
import com.acme.fromzeroapi.profiles.infrastructure.persistence.jpa.repositories.DeveloperRepository;
import com.acme.fromzeroapi.profiles.infrastructure.persistence.jpa.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final DeveloperRepository developerRepository;
    private final CompanyRepository companyRepository;


    public ProfileQueryServiceImpl(DeveloperRepository developerRepository, CompanyRepository enterpriseRepository) {
        this.developerRepository = developerRepository;
        this.companyRepository = enterpriseRepository;
    }

    @Override
    public List<Developer> handle(GetAllDevelopersQuery query) {
        return developerRepository.findAll();
    }

    @Override
    public List<Company> handle(GetAllCompaniesQuery query) {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Developer> handle(GetDeveloperByIdQuery query) {
        return this.developerRepository.findById(query.developerId());
    }

    @Override
    public Optional<Company> handle(GetCompanyByIdQuery query) {
        return companyRepository.findById(query.id());
    }

    @Override
    public Optional<Company> handle(GetCompanyByProfileIdQuery query) {
        var company = new ProfileId(query.profileId());
        return companyRepository.findByProfileId(company);
    }

    @Override
    public Optional<Developer> handle(GetDeveloperByProfileIdQuery query) {
        var developer = new ProfileId(query.profileId());
        return developerRepository.findByProfileId(developer);
    }

    @Override
    public Optional<Company> handle(GetCompanyProfileByIdOrRecordIdQuery query) {
        try {
            var id = Long.parseLong(query.id());
            return companyRepository.findById(id);
        }catch (Exception e) {
            return companyRepository.findByProfileId(new ProfileId(query.id()));
        }
    }

    @Override
    public Optional<Developer> handle(GetDeveloperProfileByIdOrRecordIdQuery query) {
        try {
            var id = Long.parseLong(query.id());
            return developerRepository.findById(id);
        }catch (Exception e) {
            return developerRepository.findByProfileId(new ProfileId(query.id()));
        }
    }

    @Override
    public Optional<Company> handle(GetCompanyByEmailQuery query) {
        return companyRepository.findByEmail(query.email());
    }

    @Override
    public Optional<Developer> handle(GetDeveloperByEmailQuery query) {
        return developerRepository.findByEmail(query.email());
    }


}
