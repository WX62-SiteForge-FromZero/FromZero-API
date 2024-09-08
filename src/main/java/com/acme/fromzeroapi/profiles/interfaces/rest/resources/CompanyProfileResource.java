package com.acme.fromzeroapi.profiles.interfaces.rest.resources;

public record CompanyProfileResource(
        Long id,
        String companyName,
        String email,
        String description,
        String country,
        String ruc,
        String phone,
        String website,
        String profileImgUrl,
        String sector
) {
}
