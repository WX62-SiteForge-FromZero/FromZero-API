package com.acme.fromzeroapi.projects.interfaces.rest.transform;

import com.acme.fromzeroapi.projects.domain.model.commands.CreateProjectCommand;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.CreateProjectResource;

public class CreateProjectCommandFromResourceAssembler {
    public static CreateProjectCommand toCommandFromResource(CreateProjectResource resource){
        return new CreateProjectCommand(
                resource.name(),
                resource.description(),
                resource.ownerId(),
                resource.languages(),
                resource.frameworks(),
                resource.type(),
                resource.budget(),
                resource.methodologies()
        );
    }
}
