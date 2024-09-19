package com.acme.fromzeroapi.projects.domain.model.commands;

public record AssignProjectDeveloperCommand(
        Long projectId,
        Long developerId
) {
}
