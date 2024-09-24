package com.acme.fromzeroapi.message.application.internal.commandServices;

import com.acme.fromzeroapi.message.domain.model.aggregates.Chat;
import com.acme.fromzeroapi.message.domain.model.commands.AddMessageCommand;
import com.acme.fromzeroapi.message.domain.model.entities.Message;
import com.acme.fromzeroapi.message.domain.services.MessageCommandService;
import com.acme.fromzeroapi.message.infrastructure.persistence.jpa.repositories.ChatRepository;
import com.acme.fromzeroapi.message.infrastructure.persistence.jpa.repositories.MessageRepository;
import com.acme.fromzeroapi.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageCommandServiceImpl implements MessageCommandService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    public MessageCommandServiceImpl(MessageRepository messageRepository, ChatRepository chatRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
    }

    @Override
    public Optional<Message> handle(AddMessageCommand command) {
        var chat = chatRepository.findById(command.chatId()).orElseThrow();
        var message = new Message(command.senderId(), command.content(), chat);
        chat.addMessage(message);
        messageRepository.save(message);
        return Optional.of(message);
    }
}
