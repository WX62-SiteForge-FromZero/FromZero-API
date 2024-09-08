package com.acme.fromzeroapi.message.domain.model.commands;

import com.acme.fromzeroapi.iam.domain.model.aggregates.User;

public record CreateMessageCommand(String subject, String emailBody, User recipient, User sender) {
}
