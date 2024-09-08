package com.acme.fromzeroapi.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(String email, String accountType, String token) {
}
