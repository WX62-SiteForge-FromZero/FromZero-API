package com.acme.fromzeroapi.payment.interfaces.rest.transform;

import com.acme.fromzeroapi.payment.domain.model.commands.CompletePaymentCommand;
import com.acme.fromzeroapi.payment.interfaces.rest.resources.CompletePaymentResource;

import java.time.LocalDate;

public class CompletePaymentCommandFromResourceAssembler {
    public static CompletePaymentCommand toCommandFromResource(CompletePaymentResource resource, Long projectId){

        String[] dateParts = resource.expirationDate().split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);

        LocalDate expirationDateYYYYMM = LocalDate.of(year, month, 1);

        return new CompletePaymentCommand(
                projectId,
                resource.cardNumber(),
                expirationDateYYYYMM,
                resource.cvv()
        );
    }
}
