package com.acme.fromzeroapi.deliverables.domain.model.commands;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;

import java.time.LocalDate;

public record CreateDefaultDeliverablesCommand(
        String name,
        String description,
        LocalDate date,
        Project project
) {
}
