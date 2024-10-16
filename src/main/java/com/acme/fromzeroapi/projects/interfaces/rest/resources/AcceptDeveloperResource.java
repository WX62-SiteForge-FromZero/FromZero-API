package com.acme.fromzeroapi.projects.interfaces.rest.resources;

public record AcceptDeveloperResource(
        Long developerId,
        Boolean accepted
) {
}
