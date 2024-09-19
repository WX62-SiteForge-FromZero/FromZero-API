package com.acme.fromzeroapi.deliverables.interfaces.rest.transform;

import com.acme.fromzeroapi.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.acme.fromzeroapi.deliverables.interfaces.rest.resourses.CreateDeliverableResource;

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
