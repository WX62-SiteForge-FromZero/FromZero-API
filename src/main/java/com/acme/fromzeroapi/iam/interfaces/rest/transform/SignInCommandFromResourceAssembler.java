package com.acme.fromzeroapi.iam.interfaces.rest.transform;

import com.acme.fromzeroapi.iam.domain.model.commands.SignInCommand;
import com.acme.fromzeroapi.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.email(), signInResource.password());
    }
}
