package com.acme.fromzeroapi.iam.interfaces.rest.transform;

import com.acme.fromzeroapi.iam.domain.model.aggregates.User;
import com.acme.fromzeroapi.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUsedResourcerFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getEmail(),user.getUserType(),token);
    }
}
