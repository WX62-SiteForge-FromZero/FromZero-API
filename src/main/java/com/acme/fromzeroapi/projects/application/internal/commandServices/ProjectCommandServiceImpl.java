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
            var company = externalProfileProjectService.getCompanyByProfileId(command.companyId());
            if (company.isEmpty()) {
                return Optional.empty();
            }

            var project = new Project(command);

            this.projectRepository.save(project);

            if (command.methodologies().isBlank()) {
                project.createDefaultDeliverables(project.getId(), project.getType());
            }else {
                project.createDeliverablesByMethodologies(project.getId(), command.methodologies());
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

        if (project.get().getDeveloperId()!=null){
            return Optional.empty();
        }

        var developer = externalProfileProjectService.getDeveloperByProfileId(command.developerId());
        if (developer.isEmpty()) {
            return Optional.empty();
        }

        project.get().getCandidateIds().add(developer.get().getProfileId().RecordId());

        this.projectRepository.save(project.get());
        return project;
    }

    @Override
    public Optional<Project> handle(AcceptProjectDeveloperCommand command) {

        var project = projectRepository.findById(command.projectId());
        if (project.isEmpty()) {
            return Optional.empty();
        }

        var developer = externalProfileProjectService.getDeveloperByProfileId(command.developerId());
        if (developer.isEmpty()) {
            return Optional.empty();
        }


        if (!project.get().getCandidateIds().contains(developer.get().getProfileId().RecordId())) {
            return Optional.empty();
        }

        if (command.accepted()) {

            project.get().setDeveloperId(developer.get().getProfileId().RecordId());
            project.get().getCandidateIds().clear();
            project.get().setState(ProjectState.EN_PROGRESO);

        }else {
            project.get().getCandidateIds().remove(developer.get().getProfileId().RecordId());
        }
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
            project.get().setProjectPayment(project.get().getId());
        }

        this.projectRepository.save(project.get());
    }

    @Override
    public void handle(FinishProjectCommand command) {
        var project = projectRepository.findById(command.projectId());
        if (project.isEmpty()) {
            return;
        }
        project.get().setState(ProjectState.COMPLETADO);
        var developer = externalProfileProjectService.getDeveloperByProfileId(project.get().getDeveloperId());
        externalProfileProjectService.updateDeveloperCompletedProjects(developer.get().getId());
        this.projectRepository.save(project.get());
    }
}
