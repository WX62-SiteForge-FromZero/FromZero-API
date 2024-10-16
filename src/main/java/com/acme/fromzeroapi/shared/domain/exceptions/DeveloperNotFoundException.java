package com.acme.fromzeroapi.shared.domain.exceptions;

public class DeveloperNotFoundException extends RuntimeException {
    public DeveloperNotFoundException(String developerRecordId) {
        super("Developer with record id " + developerRecordId + " not found");
    }
}
