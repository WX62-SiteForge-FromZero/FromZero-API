package com.acme.fromzeroapi.projects.interfaces.rest.transform;

import com.acme.fromzeroapi.projects.domain.model.commands.CreateDeliverableCommand;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.CreateDeliverableResource;

public class CreateDeliverableCommandFromResourceAssembler {
    public static CreateDeliverableCommand toCommandFromResource(CreateDeliverableResource resource){
        return new CreateDeliverableCommand(
                resource.name(),
                resource.description(),
                resource.date(),
                resource.projectId()
        );
    }
}
