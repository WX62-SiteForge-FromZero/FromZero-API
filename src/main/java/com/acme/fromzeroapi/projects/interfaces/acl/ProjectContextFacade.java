package com.acme.fromzeroapi.projects.interfaces.acl;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.domain.model.commands.UpdateProjectProgressCommand;
import com.acme.fromzeroapi.projects.domain.model.queries.GetProjectByIdQuery;
import com.acme.fromzeroapi.projects.domain.services.ProjectCommandService;
import com.acme.fromzeroapi.projects.domain.services.ProjectQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectContextFacade {
    private final ProjectQueryService projectQueryService;

    public ProjectContextFacade(ProjectQueryService projectQueryService) {
        this.projectQueryService = projectQueryService;
    }

    public Optional<Project> getProjectById(Long projectId){
        return projectQueryService.handle(new GetProjectByIdQuery(projectId));
    }

}
