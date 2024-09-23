package com.acme.fromzeroapi.projects.application.internal.queryServices;

import com.acme.fromzeroapi.projects.application.internal.outboundServices.acl.ExternalProfileProjectService;
import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.domain.model.queries.*;
import com.acme.fromzeroapi.projects.domain.services.ProjectQueryService;
import com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectQueryServiceImpl implements ProjectQueryService {
    private final ProjectRepository projectRepository;
    private final ExternalProfileProjectService externalProfileProjectService;
    public ProjectQueryServiceImpl(
            ProjectRepository projectRepository,
            ExternalProfileProjectService externalProfileProjectService ) {
        this.projectRepository = projectRepository;
        this.externalProfileProjectService = externalProfileProjectService;
    }

    @Override
    public List<Project> handle(GetAllProjectsQuery query) {
        return this.projectRepository.findAll();
    }

    @Override
    public List<Project> handle(GetAllProjectsByStateQuery query) {
        return this.projectRepository.findAllByState(query.state());
    }

    @Override
    public Optional<Project> handle(GetProjectByIdQuery query) {
        return this.projectRepository.findById(query.id());
    }

    @Override
    public List<Project> handle(GetAllProjectsByDeveloperIdQuery query) {
        var developer = externalProfileProjectService.getDeveloperById(query.developerId());
        if (developer.isEmpty()){
            return List.of();
        }
        return this.projectRepository.findAllByDeveloper(developer.get());
    }

    @Override
    public List<Project> handle(GetAllProjectsByCompanyIdQuery query) {
        var company = externalProfileProjectService.getCompanyById(query.companyId());
        if (company.isEmpty()){
            return List.of();
        }
        return this.projectRepository.findAllByCompany(company.get());
    }
}
