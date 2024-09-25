package com.acme.fromzeroapi.profiles.application.internal.commandServices;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.profiles.domain.model.commands.*;
import com.acme.fromzeroapi.profiles.domain.services.ProfileCommandService;
import com.acme.fromzeroapi.profiles.infrastructure.persistence.jpa.repositories.CompanyRepository;
import com.acme.fromzeroapi.profiles.infrastructure.persistence.jpa.repositories.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final DeveloperRepository developerRepository;
    private final CompanyRepository enterpriseRepository;

    public ProfileCommandServiceImpl(DeveloperRepository developerRepository, CompanyRepository enterpriseRepository) {
        this.developerRepository = developerRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    public Optional<Developer> handle(UpdateDeveloperCompletedProjectsCommand command) {
        var developer = developerRepository.findById(command.developerId());
        if (developer.isEmpty())return Optional.empty();
        
        int completedProjects = developer.get().getCompletedProjects();

        developer.get().setCompletedProjects(completedProjects + 1);
        this.developerRepository.save(developer.get());
        return developer;
    }

    @Override
    public Optional<Developer> handle(UpdateDeveloperProfileCommand command) {
        var developer = developerRepository.findById(command.id());
        if (developer.isEmpty())return Optional.empty();
        developer.get().setDescription(command.description());
        developer.get().setCountry(command.country());
        developer.get().setPhone(command.phone());
        developer.get().setSpecialties(command.specialties());
        developer.get().setProfileImgUrl(command.profileImgUrl());

        developerRepository.save(developer.get());

        return developer;
    }

    @Override
    public Optional<Company> handle(UpdateCompanyProfileCommand command) {
        var company= enterpriseRepository.findById(command.id());
        if (company.isEmpty())return Optional.empty();
        company.get().setDescription(command.description());
        company.get().setCountry(command.country());
        company.get().setRuc(command.ruc());
        company.get().setPhone(command.phone());
        company.get().setWebsite(command.website());
        company.get().setProfileImgUrl(command.profileImgUrl());
        company.get().setSector(command.sector());

        enterpriseRepository.save(company.get());

        return company;
    }

    @Override
    public void handle(CreateCompanyProfileCommand command) {
        var company = new Company(command);
        enterpriseRepository.save(company);
    }

    @Override
    public void handle(CreateDeveloperProfileCommand command) {
        var developer = new Developer(command);
        developerRepository.save(developer);
    }

}
