package com.acme.fromzeroapi.message.interfaces.rest.transform;

import com.acme.fromzeroapi.message.domain.model.entities.Message;
import com.acme.fromzeroapi.message.interfaces.rest.resources.MessageResource;

public class MessageResourceFromEntityAssembler {

    public static MessageResource toResourceFromEntity(Message entity) {
        return new MessageResource(
                entity.getId(),
                entity.getSenderId(),
                entity.getContent(),
                entity.getChat().getId(),
                entity.getCreatedAt()
        );
    }
}
