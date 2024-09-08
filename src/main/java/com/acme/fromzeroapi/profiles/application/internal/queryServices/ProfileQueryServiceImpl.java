package com.acme.fromzeroapi.profiles.application.internal.queryServices;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
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
    private final CompanyRepository enterpriseRepository;

    public ProfileQueryServiceImpl(DeveloperRepository developerRepository, CompanyRepository enterpriseRepository) {
        this.developerRepository = developerRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    public List<Developer> handle(GetAllDevelopersAsyncQuery query) {
        return developerRepository.findAll();
    }

    @Override
    public Optional<Developer> handle(GetDeveloperByIdQuery query) {
        return this.developerRepository.findById(query.developerId());
    }

    @Override
    public Optional<Developer> handle(GetDeveloperProfileIdByEmailQuery query) {
        return developerRepository.findByEmail(query.email());
    }

    @Override
    public Optional<Company> handle(GetCompanyProfileIdByEmailQuery query) {
        return enterpriseRepository.findByEmail(query.email());
    }

    @Override
    public Optional<Company> handle(GetCompanyByIdQuery query) {
        return enterpriseRepository.findById(query.id());
    }
}
