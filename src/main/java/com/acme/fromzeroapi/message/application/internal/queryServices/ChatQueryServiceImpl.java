package com.acme.fromzeroapi.message.application.internal.queryServices;

import com.acme.fromzeroapi.message.application.internal.outboundservices.ExternalProfileMesssageService;
import com.acme.fromzeroapi.message.domain.model.aggregates.Chat;
import com.acme.fromzeroapi.message.domain.model.entities.Message;
import com.acme.fromzeroapi.message.domain.model.queries.GetAllChatsByCompanyProfileIdQuery;
import com.acme.fromzeroapi.message.domain.model.queries.GetAllChatsByDeveloperProfileIdQuery;
import com.acme.fromzeroapi.message.domain.model.queries.GetAllMessagesByChatIdQuery;
import com.acme.fromzeroapi.message.domain.model.queries.GetChatByIdQuery;
import com.acme.fromzeroapi.message.domain.services.ChatQueryService;
import com.acme.fromzeroapi.message.infrastructure.persistence.jpa.repositories.ChatRepository;
import com.acme.fromzeroapi.message.infrastructure.persistence.jpa.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatQueryServiceImpl implements ChatQueryService {
    private final ChatRepository chatRepository;
    private final ExternalProfileMesssageService externalProfileMesssageService;

    public ChatQueryServiceImpl(ChatRepository chatRepository,  ExternalProfileMesssageService externalProfileMesssageService) {
        this.chatRepository = chatRepository;
        this.externalProfileMesssageService = externalProfileMesssageService;
    }
    @Override
    public List<Chat> handle(GetAllChatsByCompanyProfileIdQuery query) {
        var company = externalProfileMesssageService.getCompanyByProfileId(query.companyProfileId()).orElseThrow();
        return chatRepository.findAllByCompany(company);
    }

    @Override
    public List<Chat> handle(GetAllChatsByDeveloperProfileIdQuery query) {
        var developer = externalProfileMesssageService.getDeveloperByProfileId(query.developerProfileId()).orElseThrow();
        return chatRepository.findAllByDeveloper(developer);
    }

    @Override
    public Optional<Chat> handle(GetChatByIdQuery query) {
        return chatRepository.findById(query.chatId());
    }
}
