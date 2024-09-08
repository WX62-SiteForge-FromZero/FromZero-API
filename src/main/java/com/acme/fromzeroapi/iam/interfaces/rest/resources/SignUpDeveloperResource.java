package com.acme.fromzeroapi.iam.interfaces.rest.resources;

public record SignUpDeveloperResource(
        String email,
        String password,
        String firstName,
        String lastName
) {
}
