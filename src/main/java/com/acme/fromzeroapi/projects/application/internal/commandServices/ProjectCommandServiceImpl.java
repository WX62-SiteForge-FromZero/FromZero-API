package com.acme.fromzeroapi.projects.application.internal.commandServices;

import com.acme.fromzeroapi.projects.application.internal.outboundServices.acl.ExternalDeliverableService;
import com.acme.fromzeroapi.projects.application.internal.outboundServices.acl.ExternalProfileProjectService;
import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.domain.model.commands.AssignProjectDeveloperCommand;
import com.acme.fromzeroapi.projects.domain.model.commands.CreateProjectCommand;
import com.acme.fromzeroapi.projects.domain.model.commands.UpdateProjectCandidatesListCommand;
import com.acme.fromzeroapi.projects.domain.model.commands.UpdateProjectProgressCommand;
import com.acme.fromzeroapi.projects.domain.model.valueObjects.ProjectState;
import com.acme.fromzeroapi.projects.domain.services.ProjectCommandService;
import com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectCommandServiceImpl implements ProjectCommandService {

    private final ProjectRepository projectRepository;
    private final ExternalProfileProjectService externalProfileProjectService;
    private final ExternalDeliverableService externalDeliverableService;

    public ProjectCommandServiceImpl(
            ProjectRepository projectRepository,
            ExternalProfileProjectService externalProfileProjectService,
            ExternalDeliverableService externalDeliverableService ) {

        this.projectRepository = projectRepository;
        this.externalProfileProjectService = externalProfileProjectService;
        this.externalDeliverableService = externalDeliverableService;
    }

    @Override
    public Optional<Project> handle(CreateProjectCommand command) {

        try {
            var company = externalProfileProjectService.getCompanyById(command.companyId());
            if (company.isEmpty()) {
                return Optional.empty();
            }

            var project = new Project(command, company.get());

            this.projectRepository.save(project);

            this.externalDeliverableService.createDeliverables(project);

            return Optional.of(project);
        }catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Project> handle(UpdateProjectCandidatesListCommand command) {

        var project = projectRepository.findById(command.projectId());
        if (project.isEmpty()) {
            return Optional.empty();
        }
        var developer = externalProfileProjectService.getDeveloperById(command.developerId());
        if (developer.isEmpty()) {
            return Optional.empty();
        }

        project.get().getCandidates().add(developer.get());

        this.projectRepository.save(project.get());
        return project;
    }

    @Override
    public Optional<Project> handle(AssignProjectDeveloperCommand command) {

        var project = projectRepository.findById(command.projectId());
        if (project.isEmpty()) {
            return Optional.empty();
        }

        var developer = externalProfileProjectService.getDeveloperById(command.developerId());
        if (developer.isEmpty()) {
            return Optional.empty();
        }

        project.get().setDeveloper(developer.get());
        project.get().getCandidates().clear();
        project.get().setState(ProjectState.EN_PROGRESO);

        this.projectRepository.save(project.get());
        return project;
    }

    @Override
    public Optional<Project> handle(UpdateProjectProgressCommand command) {
        var project = command.project();
        project.setProgress(command.progress());
        this.projectRepository.save(project);
        return Optional.of(project);
    }
}
