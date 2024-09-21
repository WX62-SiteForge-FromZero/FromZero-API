package com.acme.fromzeroapi.projects.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateDeliverableResource(
        String name,
        String description,
        LocalDate date,
        Long projectId
) {
}
