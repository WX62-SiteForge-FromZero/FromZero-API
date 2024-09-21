package com.acme.fromzeroapi.projects.interfaces.rest.resources;

import com.acme.fromzeroapi.projects.domain.model.valueObjects.DeliverableState;

import java.time.LocalDate;

public record DeliverableResource(
        Long id,
        String name,
        String description,
        LocalDate date,
        DeliverableState state,
        String developerMessage,
        // Project project,
        Long projectId
) {
}
