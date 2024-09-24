package com.acme.fromzeroapi.message.domain.services;

import com.acme.fromzeroapi.message.domain.model.entities.Message;
import com.acme.fromzeroapi.message.domain.model.queries.GetAllMessagesByChatIdQuery;

import java.util.List;
import java.util.Optional;

public interface MessageQueryService {
    List<Message> handle(GetAllMessagesByChatIdQuery query);
}
