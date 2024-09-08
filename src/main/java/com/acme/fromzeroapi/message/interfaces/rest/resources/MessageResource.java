package com.acme.fromzeroapi.message.interfaces.rest.resources;

import java.time.LocalDate;

public record MessageResource(
        Long id,
        String subject,
        String emailBody,
        //User recipient,
        String recipientEmail,
        //User sender,
        String senderEmail,
        LocalDate sentTime
) {
}
