package com.acme.fromzeroapi.projects.domain.model.commands;

public record AcceptProjectDeveloperCommand(
        Long projectId,
        Long developerId,
        Boolean accepted
) {
}
