package com.acme.fromzeroapi.projects.interfaces.rest.transform;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.ProjectResource;

public class ProjectResourceFromEntityAssembler {
    public static ProjectResource toResourceFromEntity(Project entity){
        return new ProjectResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getState(),
                entity.getProgress(),
                entity.getCompany(),
                entity.getDeveloper(),
                entity.getCandidates(),
                entity.getLanguages(),
                entity.getFrameworks(),
                entity.getType(),
                entity.getBudget(),
                entity.getMethodologies()
        );
    }
}
