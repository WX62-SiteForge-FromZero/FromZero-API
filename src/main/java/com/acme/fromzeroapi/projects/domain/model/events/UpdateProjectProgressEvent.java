package com.acme.fromzeroapi.projects.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public final class UpdateProjectProgressEvent  extends ApplicationEvent {
    private final Long projectId;
    private final Long completedDeliverables;
    private final Integer totalDeliverables;

    public UpdateProjectProgressEvent(
            Object source,
            Long projectId,
            Long completedDeliverables,
            Integer totalDeliverables) {

        super(source);
        this.projectId = projectId;
        this.completedDeliverables = completedDeliverables;
        this.totalDeliverables = totalDeliverables;
    }
}
