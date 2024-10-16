package com.acme.fromzeroapi.projects.application.internal.eventHandlers;

import com.acme.fromzeroapi.projects.domain.model.commands.CreateDeliverableCommand;
import com.acme.fromzeroapi.projects.domain.model.events.CreateDeliverablesByMethodologiesEvent;
import com.acme.fromzeroapi.projects.domain.services.DeliverableCommandService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class CreateDeliverablesByMethodologiesEventHandler {
    private final DeliverableCommandService deliverableCommandService;

    public CreateDeliverablesByMethodologiesEventHandler(DeliverableCommandService deliverableCommandService) {
        this.deliverableCommandService = deliverableCommandService;
    }

    @EventListener(CreateDeliverablesByMethodologiesEvent.class)
    public void on(CreateDeliverablesByMethodologiesEvent event) {
        LocalDate today = LocalDate.now();
        var deliverablesCommandList = new ArrayList<CreateDeliverableCommand>();
        String[] objects = event.getMethodologies().split("\\|");
        for (String object : objects) {
            String[] parts = object.split("~",2);
            if (parts.length == 2) {
                String name = parts[0];
                String description = parts[1];
                deliverablesCommandList.add(new CreateDeliverableCommand(name,description,today,event.getProjectId()));
                today=today.plusWeeks(1);
            }
        }
        deliverableCommandService.handle(deliverablesCommandList);
    }
}
