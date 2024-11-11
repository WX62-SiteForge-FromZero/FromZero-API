package com.acme.fromzeroapi.projects.domain.model.commands;

public record AcceptProjectDeveloperCommand(
        Long projectId,
        String developerId,
        Boolean accepted
) {
}
