package com.acme.fromzeroapi.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record ProfileId(String RecordId) {
    public ProfileId() {
        this(UUID.randomUUID().toString());
    }
}
