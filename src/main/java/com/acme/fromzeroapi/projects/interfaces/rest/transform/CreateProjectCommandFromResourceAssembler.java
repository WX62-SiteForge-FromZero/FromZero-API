package com.acme.fromzeroapi.projects.interfaces.rest.transform;

import com.acme.fromzeroapi.projects.domain.model.commands.CreateProjectCommand;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.CreateProjectResource;

public class CreateProjectCommandFromResourceAssembler {
    public static CreateProjectCommand toCommandFromResource(CreateProjectResource resource) {

        String compressedDeliverables = "";
        if (!resource.methodologies().isEmpty()) {
            var methodologiesList = resource.methodologies();
            StringBuilder stringBuilder = new StringBuilder();

            methodologiesList.forEach(item -> {
                stringBuilder
                        .append(item.name())
                        .append("~")
                        .append(item.description());
                if (!methodologiesList.getLast().equals(item)) {
                    stringBuilder.append("|");
                }
            });

            compressedDeliverables = stringBuilder.toString();
        }
        return new CreateProjectCommand(
                resource.name(),
                resource.description(),
                resource.ownerId(),
                resource.languages(),
                resource.frameworks(),
                resource.type(),
                resource.budget(),
                resource.currency(),
                compressedDeliverables
        );
    }
}
