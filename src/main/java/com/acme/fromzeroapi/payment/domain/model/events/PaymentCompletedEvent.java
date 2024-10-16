package com.acme.fromzeroapi.payment.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public final class PaymentCompletedEvent extends ApplicationEvent {
    private Long projectId;
    public PaymentCompletedEvent(Object source, Long projectId) {
        super(source);
        this.projectId = projectId;
    }
}
