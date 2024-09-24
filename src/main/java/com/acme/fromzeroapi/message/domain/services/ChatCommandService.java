package com.acme.fromzeroapi.message.domain.services;

import com.acme.fromzeroapi.message.domain.model.aggregates.Chat;
import com.acme.fromzeroapi.message.domain.model.commands.AddMessageCommand;
import com.acme.fromzeroapi.message.domain.model.commands.CreateChatCommand;

import java.util.List;
import java.util.Optional;

public interface ChatCommandService {
    Optional<Long> handle(CreateChatCommand command);
}
