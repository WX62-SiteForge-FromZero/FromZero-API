package com.acme.fromzeroapi.projects.domain.model.events;


import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public final class DeliverableCompletedEvent extends ApplicationEvent {

    private final Long projectId;

    public DeliverableCompletedEvent(Object source, Long projectId) {
        super(source);
        this.projectId = projectId;
    }
}
