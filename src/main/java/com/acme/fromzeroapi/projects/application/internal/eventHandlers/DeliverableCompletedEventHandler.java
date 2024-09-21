package com.acme.fromzeroapi.projects.application.internal.eventHandlers;

import com.acme.fromzeroapi.projects.domain.model.events.DeliverableCompletedEvent;
import com.acme.fromzeroapi.projects.domain.model.queries.GetAllDeliverablesByProjectIdQuery;
import com.acme.fromzeroapi.projects.domain.model.queries.GetCompletedDeliverablesQuery;
import com.acme.fromzeroapi.projects.domain.services.DeliverableQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DeliverableCompletedEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliverableCompletedEventHandler.class);

    private final DeliverableQueryService deliverableQueryService;

    public DeliverableCompletedEventHandler(
            DeliverableQueryService deliverableQueryService) {

        this.deliverableQueryService = deliverableQueryService;
    }

    @EventListener(DeliverableCompletedEvent.class)
    public void on(DeliverableCompletedEvent event) {
        var completedDeliverables = deliverableQueryService.handle(new GetCompletedDeliverablesQuery(event.getProjectId()));
        var deliverables = deliverableQueryService.handle(new GetAllDeliverablesByProjectIdQuery(event.getProjectId()));
        var totalDeliverables = deliverables.size();

        LOGGER.info("LOS DATOS SON\ncompleteados: {}\ntotal: {}", completedDeliverables, totalDeliverables);

        //externalProjectDeliverableService.updateProjectProgress(event.getProjectId(),completedDeliverables,totalDeliverables);
    }
}
