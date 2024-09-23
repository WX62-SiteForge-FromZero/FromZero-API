package com.acme.fromzeroapi.iam.domain.model.commands;

public record SignUpCompanyCommand(
        String email,
        String password,
        String companyName
) {
}
