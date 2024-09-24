package com.acme.fromzeroapi.message.application.internal.queryServices;

import com.acme.fromzeroapi.message.domain.model.entities.Message;
import com.acme.fromzeroapi.message.domain.model.queries.GetAllMessagesByChatIdQuery;
import com.acme.fromzeroapi.message.domain.services.MessageQueryService;
import com.acme.fromzeroapi.message.infrastructure.persistence.jpa.repositories.ChatRepository;
import com.acme.fromzeroapi.message.infrastructure.persistence.jpa.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageQueryServiceImpl implements MessageQueryService {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    public MessageQueryServiceImpl(ChatRepository chatRepository, MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> handle(GetAllMessagesByChatIdQuery query) {
        var chat = chatRepository.findById(query.chatId()).orElseThrow();
        return messageRepository.findAllByChatOrderByCreatedAt(chat);
    }
}
