package com.acme.fromzeroapi.projects.domain.model.commands;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;

public record UpdateProjectProgressCommand(
        Long projectId,
        Long completedDeliverables,
        Integer totalDeliverables
) {
}
