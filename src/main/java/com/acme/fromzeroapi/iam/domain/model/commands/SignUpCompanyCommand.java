package com.acme.fromzeroapi.iam.domain.model.commands;

public record SignUpCompanyCommand(
        String companyName,
        String email,
        String password
) {
}
