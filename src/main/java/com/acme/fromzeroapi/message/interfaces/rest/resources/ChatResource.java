package com.acme.fromzeroapi.message.interfaces.rest.resources;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;

import java.util.Date;

public record ChatResource(
        Long id,
        String developer,
        String company,
        Date createdAt
) {
}
