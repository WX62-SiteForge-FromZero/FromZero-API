package com.acme.fromzeroapi.message.domain.services;

import com.acme.fromzeroapi.message.domain.model.aggregates.Chat;
import com.acme.fromzeroapi.message.domain.model.queries.GetAllChatsByCompanyProfileIdQuery;
import com.acme.fromzeroapi.message.domain.model.queries.GetAllChatsByDeveloperProfileIdQuery;
import com.acme.fromzeroapi.message.domain.model.queries.GetChatByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ChatQueryService {
    List<Chat> handle(GetAllChatsByCompanyProfileIdQuery query);
    List<Chat> handle(GetAllChatsByDeveloperProfileIdQuery query);
    Optional<Chat> handle(GetChatByIdQuery query);
}
