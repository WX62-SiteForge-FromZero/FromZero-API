package com.acme.fromzeroapi.iam.interfaces.rest.transform;


import com.acme.fromzeroapi.iam.domain.model.aggregates.User;
import com.acme.fromzeroapi.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(user.getId(), user.getUserType());
    }
}
