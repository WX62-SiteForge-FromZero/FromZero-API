package com.acme.fromzeroapi.projects.application.internal.eventHandlers;

import com.acme.fromzeroapi.projects.domain.model.commands.UpdateProjectProgressCommand;
import com.acme.fromzeroapi.projects.domain.model.events.UpdateProjectProgressEvent;
import com.acme.fromzeroapi.projects.domain.services.ProjectCommandService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UpdateProjectProgressEventHandler {
    private final ProjectCommandService projectCommandService;

    public UpdateProjectProgressEventHandler(ProjectCommandService projectCommandService) {
        this.projectCommandService = projectCommandService;
    }

    @EventListener(UpdateProjectProgressEvent.class)
    public void on(UpdateProjectProgressEvent event) {
        projectCommandService.handle(
                new UpdateProjectProgressCommand(
                        event.getProjectId(),
                        event.getCompletedDeliverables(),
                        event.getTotalDeliverables()
                )
        );
    }
}
