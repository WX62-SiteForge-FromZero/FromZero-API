package com.acme.fromzeroapi.deliverables.interfaces.rest.resourses;

import java.time.LocalDate;

public record CreateDeliverableResource(
        String name, String description, LocalDate date, Long projectId) {
}
