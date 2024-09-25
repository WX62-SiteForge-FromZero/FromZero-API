package com.acme.fromzeroapi.projects.application.internal.commandServices;

import com.acme.fromzeroapi.projects.application.internal.outboundServices.acl.ExternalProfileProjectService;
import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.domain.model.commands.*;
import com.acme.fromzeroapi.projects.domain.model.valueObjects.ProjectState;
import com.acme.fromzeroapi.projects.domain.services.ProjectCommandService;
import com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectCommandServiceImpl implements ProjectCommandService {

    private final ProjectRepository projectRepository;
    private final ExternalProfileProjectService externalProfileProjectService;

    private final ApplicationEventPublisher eventPublisher;

    public ProjectCommandServiceImpl(
            ProjectRepository projectRepository,
            ExternalProfileProjectService externalProfileProjectService,
            ApplicationEventPublisher eventPublisher) {

        this.projectRepository = projectRepository;
        this.externalProfileProjectService = externalProfileProjectService;
        this.eventPublisher = eventPublisher;
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

            if (project.getMethodologies().isBlank()) {
                project.createDefaultDeliverables(project.getId(), project.getType());
            }

            project.getDomainEvents().forEach(eventPublisher::publishEvent);


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

        if (project.get().getDeveloper()!=null){
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


        if(!project.get().getCandidates().contains(developer.get())){
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
            externalProfileProjectService.updateDeveloperCompletedProjects(project.get().getDeveloper().getId());
        }

        this.projectRepository.save(project.get());
    }
}
