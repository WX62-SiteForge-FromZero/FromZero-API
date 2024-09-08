package com.acme.fromzeroapi.profiles.interfaces.rest.transform;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.profiles.interfaces.rest.resources.CompanyProfileResource;

public class CompanyProfileResourceFromEntityAssembler {
    public static CompanyProfileResource toResourceFromEntity(Company entity){
        return new CompanyProfileResource(
                entity.getId(),
                entity.getCompanyName(),
                entity.getEmail(),
                entity.getDescription(),
                entity.getCountry(),
                entity.getRuc(),
                entity.getPhone(),
                entity.getWebsite(),
                entity.getProfileImgUrl(),
                entity.getSector()
        );
    }
}
