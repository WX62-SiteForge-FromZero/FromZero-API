package com.acme.fromzeroapi.iam.domain.model.commands;

public record CreateUserCommand(
        String email,
        String password,
        String userType) {
}
