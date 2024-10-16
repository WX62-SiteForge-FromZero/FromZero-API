package com.acme.fromzeroapi.projects.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public final class CreateDeliverablesByMethodologiesEvent extends ApplicationEvent {
    private Long projectId;
    private String methodologies;
    public CreateDeliverablesByMethodologiesEvent(Object source, Long projectId,String methodologies) {
        super(source);
        this.projectId = projectId;
        this.methodologies = methodologies;
    }
}
