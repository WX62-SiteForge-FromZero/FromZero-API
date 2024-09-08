package com.acme.fromzeroapi.profiles.interfaces.rest.resources;

public record DeveloperProfileResource(
        Long id,
        String firstName,
        String lastName,
        String email,
        String description,
        String country,
        String phone,
        Integer completedProjects,
        String specialties,
        String profileImgUrl
) {
}
