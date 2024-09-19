package com.acme.fromzeroapi.deliverables.application.internal.outboundServices.acl;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.interfaces.acl.ProjectContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProjectDeliverableService {
    private final ProjectContextFacade projectContextFacade;

    public ExternalProjectDeliverableService(ProjectContextFacade projectContextFacade) {
        this.projectContextFacade = projectContextFacade;
    }

    public Optional<Project> getProjectById(Long projectId){
        return projectContextFacade.getProjectById(projectId);
    }

    public Optional<Project> updateProjectProgress(Long projectId){
        return projectContextFacade.updateProjectProgress();
    }
}
