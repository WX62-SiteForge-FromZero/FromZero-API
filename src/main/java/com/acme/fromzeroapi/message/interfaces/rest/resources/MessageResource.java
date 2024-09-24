package com.acme.fromzeroapi.message.interfaces.rest.resources;

import com.acme.fromzeroapi.message.domain.model.aggregates.Chat;

import java.time.LocalDate;
import java.util.Date;

public record MessageResource(
        Long id,
        String senderId,
        String content,
        Long chatId,
        Date createdAt
) {
}
