package com.acme.fromzeroapi.message.domain.services;

import com.acme.fromzeroapi.message.domain.model.commands.AddMessageCommand;
import com.acme.fromzeroapi.message.domain.model.entities.Message;

import java.util.Optional;

public interface MessageCommandService {
    Optional<Message> handle(AddMessageCommand command);
}
