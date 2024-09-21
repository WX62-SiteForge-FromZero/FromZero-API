package com.acme.fromzeroapi.projects.application.internal.commandServices;

import com.acme.fromzeroapi.projects.application.internal.outboundServices.acl.ExternalProfileProjectService;
import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.domain.model.commands.*;
import com.acme.fromzeroapi.projects.domain.model.valueObjects.ProjectState;
import com.acme.fromzeroapi.projects.domain.services.ProjectCommandService;
import com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectCommandServiceImpl implements ProjectCommandService {

    private final ProjectRepository projectRepository;
    private final ExternalProfileProjectService externalProfileProjectService;

    public ProjectCommandServiceImpl(
            ProjectRepository projectRepository,
            ExternalProfileProjectService externalProfileProjectService) {

        this.projectRepository = projectRepository;
        this.externalProfileProjectService = externalProfileProjectService;
    }

    public void createDefaultDeliverables(Long projectId) {
        var project = projectRepository.findById(projectId);
        if (project.isEmpty()) {
            return;
        }
        if (project.get().getMethodologies().isBlank()) {
            project.get().createDefaultDeliverables(project.get().getId(), project.get().getType());
        }
        projectRepository.save(project.get());
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

            this.createDefaultDeliverables(project.getId());

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
    public void handle(UpdateProjectProgressCommand command) {

        var project = projectRepository.findById(command.projectId());
        if (project.isEmpty()) {
            return;
        }
        double percentComplete = (double) command.completedDeliverables() / command.totalDeliverables() * 100;
        project.get().setProgress(percentComplete);

        if (project.get().getProgress()==100.0){
            project.get().setState(ProjectState.COMPLETADO);
        }

        this.projectRepository.save(project.get());

        /*if (project.get().getProgress()==100.0){
            project.get().sendToHighlightProject();
        }*/
    }
}
