package com.acme.fromzeroapi.message.interfaces.rest.transform;

import com.acme.fromzeroapi.message.domain.model.commands.CreateChatCommand;
import com.acme.fromzeroapi.message.interfaces.rest.resources.CreateChatCommandResource;

public class CreateChatCommandFromResourceAssembler {
    public static CreateChatCommand toCommandFromResource(CreateChatCommandResource resource) {
        return new CreateChatCommand(
                resource.developerId(),
                resource.companyId()
        );
    }
}
