package com.acme.fromzeroapi.message.interfaces.rest.transform;

import com.acme.fromzeroapi.message.domain.model.aggregates.Chat;
import com.acme.fromzeroapi.message.interfaces.rest.resources.ChatResource;

public class ChatResourceFromEntityAssembler {
    public static ChatResource toResourceFromEntity(Chat entity) {
        return new ChatResource(
                entity.getId(),
                /*entity.getDeveloper().getProfileId().RecordId(),
                entity.getCompany().getProfileId().RecordId(),*/
                entity.getDeveloper(),
                entity.getCompany(),
                entity.getCreatedAt()
        );
    }
}
