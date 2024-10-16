package com.acme.fromzeroapi.projects.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public final class SetProjectPaymentEvent extends ApplicationEvent {
    private Long projectId;

    public SetProjectPaymentEvent(Object source, Long projectId) {
        super(source);
        this.projectId = projectId;
    }

}
