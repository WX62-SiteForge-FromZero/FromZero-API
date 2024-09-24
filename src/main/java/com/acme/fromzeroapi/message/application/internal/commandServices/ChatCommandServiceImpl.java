package com.acme.fromzeroapi.message.application.internal.commandServices;

import com.acme.fromzeroapi.message.domain.model.aggregates.Chat;
import com.acme.fromzeroapi.message.domain.model.commands.CreateChatCommand;
import com.acme.fromzeroapi.message.domain.services.ChatCommandService;
import com.acme.fromzeroapi.message.infrastructure.persistence.jpa.repositories.ChatRepository;
import com.acme.fromzeroapi.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatCommandServiceImpl implements ChatCommandService {
    private final ProfileContextFacade profileContextFacade;
    private final ChatRepository chatRepository;


    public ChatCommandServiceImpl(ProfileContextFacade profileContextFacade, ChatRepository chatRepository) {
        this.profileContextFacade = profileContextFacade;
        this.chatRepository = chatRepository;
    }

    @Override
    public Optional<Long> handle(CreateChatCommand command) {
        var company = profileContextFacade.getCompanyByProfileId(command.companyId()).orElseThrow();
        var developer = profileContextFacade.getDeveloperByProfileId(command.developerId()).orElseThrow();
        var chat = new Chat(developer, company);
        chatRepository.save(chat);
        return Optional.of(chat.getId());
    }
}
