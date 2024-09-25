package com.acme.fromzeroapi.profiles.interfaces.rest.transform;

import com.acme.fromzeroapi.profiles.domain.model.commands.UpdateCompanyProfileCommand;
import com.acme.fromzeroapi.profiles.interfaces.rest.resources.UpdateCompanyProfileResource;

public class UpdateCompanyProfileCommandFromResourceAssembler {
    public static UpdateCompanyProfileCommand toCommandFromResource(Long id, UpdateCompanyProfileResource resource){
        return new UpdateCompanyProfileCommand(
                id,
                resource.description(),
                resource.country(),
                resource.ruc(),
                resource.phone(),
                resource.website(),
                resource.profileImgUrl(),
                resource.sector()
        );
    }
}
