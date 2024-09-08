package com.acme.fromzeroapi.projects.application.internal.outboundServices.acl;

import com.acme.fromzeroapi.deliverables.interfaces.acl.DeliverableContextFacade;
import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import org.springframework.stereotype.Service;

@Service
public class ExternalDeliverableService {
    private final DeliverableContextFacade deliverableContextFacade;

    public ExternalDeliverableService(DeliverableContextFacade deliverableContextFacade) {
        this.deliverableContextFacade = deliverableContextFacade;
    }

    public void createDeliverables(Project project){
        deliverableContextFacade.createDeliverables(project);
    }
}
