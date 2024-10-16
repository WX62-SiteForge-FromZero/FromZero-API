package com.acme.fromzeroapi.message.interfaces.rest.resources;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;

import java.util.Date;

public record ChatResource(
        Long id,
        /*String developerId,
        String companyId,*/
        Developer developer,
        Company company,
        Date createdAt
) {

}
