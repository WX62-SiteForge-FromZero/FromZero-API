package com.acme.fromzeroapi.profiles.interfaces.rest.resources;

public record UpdateCompanyProfileResource(
        String description,
        String country,
        String ruc,
        String phone,
        String website,
        String profileImgUrl,
        String sector
) {
}
