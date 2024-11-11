package com.acme.fromzeroapi.profiles.interfaces.rest.transform;

import com.acme.fromzeroapi.profiles.domain.model.commands.UpdateDeveloperProfileCommand;
import com.acme.fromzeroapi.profiles.interfaces.rest.resources.UpdateDeveloperProfileResource;

public class UpdateDeveloperProfileCommandFromResourceAssembler {
    public static UpdateDeveloperProfileCommand toCommandFromResource(String id, UpdateDeveloperProfileResource resource) {
        return new UpdateDeveloperProfileCommand(
                id,
                resource.description(),
                resource.country(),
                resource.phone(),
                resource.specialties(),
                resource.profileImgUrl()
        );
    }
}
