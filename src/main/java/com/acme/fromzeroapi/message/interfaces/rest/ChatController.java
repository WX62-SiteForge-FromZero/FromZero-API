package com.acme.fromzeroapi.message.interfaces.rest;

import com.acme.fromzeroapi.message.domain.model.queries.GetAllChatsByCompanyProfileIdQuery;
import com.acme.fromzeroapi.message.domain.model.queries.GetAllChatsByDeveloperProfileIdQuery;
import com.acme.fromzeroapi.message.domain.model.queries.GetChatByIdQuery;
import com.acme.fromzeroapi.message.domain.services.ChatCommandService;
import com.acme.fromzeroapi.message.domain.services.ChatQueryService;
import com.acme.fromzeroapi.message.interfaces.rest.resources.ChatResource;
import com.acme.fromzeroapi.message.interfaces.rest.resources.CreateChatCommandResource;
import com.acme.fromzeroapi.message.interfaces.rest.transform.ChatResourceFromEntityAssembler;
import com.acme.fromzeroapi.message.interfaces.rest.transform.CreateChatCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/v1/api/chat")
@Tag(name = "Chat", description = "Chat management endpoint")
public class ChatController {
    private final ChatCommandService chatCommandService;
    private final ChatQueryService chatQueryService;

    public ChatController(ChatCommandService chatCommandService, ChatQueryService chatQueryService) {
        this.chatCommandService = chatCommandService;
        this.chatQueryService = chatQueryService;
    }

    @Operation(summary = "Get all chats by company id")
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<ChatResource>> listChatsByCompanyId(@PathVariable String companyId) {
        var getAllChatsByCompanyProfileIdQuery = new GetAllChatsByCompanyProfileIdQuery(companyId);
        var chats = chatQueryService.handle(getAllChatsByCompanyProfileIdQuery);
        if(chats.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var chatResources = chats.stream()
                .map(ChatResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(chatResources);
    }

    @Operation(summary = "Get all chats by developer id")
    @GetMapping("/developer/{developerId}")
    public ResponseEntity<List<ChatResource>> listChatsByDeveloperId(@PathVariable String developerId) {
        var getAllChatsByDeveloperProfileIdQuery = new GetAllChatsByDeveloperProfileIdQuery(developerId);
        var chats = chatQueryService.handle(getAllChatsByDeveloperProfileIdQuery);
        if(chats.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var chatResources = chats.stream()
                .map(ChatResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(chatResources);
    }

    @Operation(summary = "Create a chat")
    @PostMapping("/chats/create")
    public ResponseEntity<ChatResource> createChat(@RequestBody CreateChatCommandResource createChatCommandResource) {
        var createChatCommand = CreateChatCommandFromResourceAssembler.toCommandFromResource(createChatCommandResource);
        var chatId = chatCommandService.handle(createChatCommand);
        if(chatId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var getChatByIdQuery = new GetChatByIdQuery(chatId.get());
        var chat = chatQueryService.handle(getChatByIdQuery);
        var chatResource = ChatResourceFromEntityAssembler.toResourceFromEntity(chat.get());
        return new ResponseEntity<>(chatResource, HttpStatus.CREATED);
    }
}
