package com.acme.fromzeroapi.message.interfaces.rest.transform;

import com.acme.fromzeroapi.message.domain.model.commands.AddMessageCommand;
import com.acme.fromzeroapi.message.interfaces.rest.resources.AddMessageCommandResource;

public class AddMessageCommandFromResourceAssembler {
        public static AddMessageCommand toCommandFromResource(AddMessageCommandResource resource) {
            return new AddMessageCommand(
                    resource.chatId(),
                    resource.senderId(),
                    resource.content()
            );
        }
}
