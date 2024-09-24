package com.acme.fromzeroapi.message.interfaces.rest;

import com.acme.fromzeroapi.message.domain.model.queries.GetAllMessagesByChatIdQuery;
import com.acme.fromzeroapi.message.domain.services.MessageCommandService;
import com.acme.fromzeroapi.message.domain.services.MessageQueryService;
import com.acme.fromzeroapi.message.interfaces.rest.resources.AddMessageCommandResource;
import com.acme.fromzeroapi.message.interfaces.rest.resources.MessageResource;
import com.acme.fromzeroapi.message.interfaces.rest.transform.AddMessageCommandFromResourceAssembler;
import com.acme.fromzeroapi.message.interfaces.rest.transform.MessageResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/v1/api/messages")
@Tag(name = "Messages", description = "Messages management endpoint")
public class MessageController {

    private final MessageCommandService messageCommandService;
    private final MessageQueryService messageQueryService;

    public MessageController(
            MessageCommandService messageCommandService,
            MessageQueryService messageQueryService) {
        this.messageCommandService = messageCommandService;
        this.messageQueryService = messageQueryService;
    }

    @Operation(summary = "Get all messages by chat id")
    @GetMapping("/send/{chatId}")
    public ResponseEntity<List<MessageResource>> listMessagesByChatId(@PathVariable Long chatId) {
        var getAllMessagesByChatIdQuery = new GetAllMessagesByChatIdQuery(chatId);
        var messages = messageQueryService.handle(getAllMessagesByChatIdQuery);
        if(messages.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var messageResources = messages.stream()
                .map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(messageResources);
    }

    @Operation(summary = "Send a message")
    @PostMapping("/messages")
    public ResponseEntity<MessageResource> sendMessage(@RequestBody AddMessageCommandResource addMessageCommandResource) {
        var addMessageCommand = AddMessageCommandFromResourceAssembler.toCommandFromResource(addMessageCommandResource);
        var message = messageCommandService.handle(addMessageCommand);
        if(message.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var messageResource = MessageResourceFromEntityAssembler.toResourceFromEntity(message.get());
        return new ResponseEntity<>(messageResource, HttpStatus.CREATED);
    }

}
