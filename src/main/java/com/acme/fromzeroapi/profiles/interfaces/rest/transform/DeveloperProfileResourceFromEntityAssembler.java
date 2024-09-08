package com.acme.fromzeroapi.profiles.interfaces.rest.transform;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.profiles.interfaces.rest.resources.DeveloperProfileResource;

public class DeveloperProfileResourceFromEntityAssembler {
    public static DeveloperProfileResource toResourceFromEntity(Developer entity){
        return new DeveloperProfileResource(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getDescription(),
                entity.getCountry(),
                entity.getPhone(),
                entity.getCompletedProjects(),
                entity.getSpecialties(),
                entity.getProfileImgUrl()
        );
    }
}
