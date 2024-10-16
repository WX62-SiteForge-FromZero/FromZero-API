package com.acme.fromzeroapi.payment.interfaces.rest.transform;

import com.acme.fromzeroapi.payment.domain.model.aggregates.Payment;
import com.acme.fromzeroapi.payment.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {
    public static PaymentResource toResourceFromEntity(Payment entity){
        return new PaymentResource(
          entity.getId(),
          entity.getDeveloper().getId(),
          entity.getProject().getId(),
          entity.getAmount().paymentAmountToString(),
          entity.getStatus()
        );
    }
}
