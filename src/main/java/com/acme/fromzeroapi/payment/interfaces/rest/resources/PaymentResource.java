package com.acme.fromzeroapi.payment.interfaces.rest.resources;

import com.acme.fromzeroapi.payment.domain.model.valueObjects.PaymentStatus;

public record PaymentResource(
        Long id,
        String developerId,
        Long projectId,
        String amount,
        String status

){

}
