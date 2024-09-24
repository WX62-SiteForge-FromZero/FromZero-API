package com.acme.fromzeroapi.message.interfaces.rest.resources;

public record AddMessageCommandResource(Long chatId, String senderId,  String content) {
}
