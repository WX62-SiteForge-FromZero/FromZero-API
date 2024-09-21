package com.acme.fromzeroapi.projects.domain.model.commands;

public record UpdateDeliverableStatusCommand(Long deliverableId, Boolean accepted) {
}
