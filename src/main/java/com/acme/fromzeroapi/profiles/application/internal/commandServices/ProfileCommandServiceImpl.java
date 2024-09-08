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
        var updatedDeveloper = command.developer();
        updatedDeveloper.setCompletedProjects(command.addProject());
        this.developerRepository.save(updatedDeveloper);
        return Optional.of(updatedDeveloper);
    }

    @Override
    public Optional<Developer> handle(UpdateDeveloperProfileCommand command) {
        var developer = developerRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Developer with id " + command.id() + " not found"));

        developer.setDescription(command.description());
        developer.setCountry(command.country());
        developer.setPhone(command.phone());
        developer.setSpecialties(command.specialties());
        developer.setProfileImgUrl(command.profileImgUrl());

        developerRepository.save(developer);

        return Optional.of(developer);
    }

    @Override
    public Optional<Company> handle(UpdateCompanyProfileCommand command) {
        var enterprise= enterpriseRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Company with id " + command.id() + " not found"));
        enterprise.setDescription(command.description());
        enterprise.setCountry(command.country());
        enterprise.setRuc(command.ruc());
        enterprise.setPhone(command.phone());
        enterprise.setWebsite(command.website());
        enterprise.setProfileImgUrl(command.profileImgUrl());
        enterprise.setSector(command.sector());

        enterpriseRepository.save(enterprise);

        return Optional.of(enterprise);
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
