package com.acme.fromzeroapi.iam.domain.model.commands;

public record SignUpDeveloperCommand(
        String email,
        String password,
        String firstName,
        String lastName
){}