package com.acme.fromzeroapi.projects.domain.model.commands;

import java.time.LocalDate;

public record CreateDeliverableCommand(
        String name,
        String description,
        LocalDate date,
        Long projectId
) {
}
