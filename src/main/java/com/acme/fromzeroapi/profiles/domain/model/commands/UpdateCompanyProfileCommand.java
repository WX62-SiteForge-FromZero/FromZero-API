package com.acme.fromzeroapi.profiles.domain.model.commands;

public record UpdateCompanyProfileCommand(
        String id,
        String description,
        String country,
        String ruc,
        String phone,
        String website,
        String profileImgUrl,
        String sector
) {
}
