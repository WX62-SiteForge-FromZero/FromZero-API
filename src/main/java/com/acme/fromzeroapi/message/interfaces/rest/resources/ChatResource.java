package com.acme.fromzeroapi.message.interfaces.rest.resources;

import java.util.Date;

public record ChatResource(
        Long id,
        String developerId,
        String companyId,
        Date createdAt
) {

}
