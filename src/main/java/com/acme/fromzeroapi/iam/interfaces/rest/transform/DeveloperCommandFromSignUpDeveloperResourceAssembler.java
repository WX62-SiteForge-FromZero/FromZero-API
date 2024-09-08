package com.acme.fromzeroapi.iam.interfaces.rest.transform;

import com.acme.fromzeroapi.iam.domain.model.commands.SignUpDeveloperCommand;
import com.acme.fromzeroapi.iam.interfaces.rest.resources.SignUpDeveloperResource;

public class DeveloperCommandFromSignUpDeveloperResourceAssembler {
    public static SignUpDeveloperCommand toCommandFromResource(SignUpDeveloperResource signUpDeveloperResource) {
        return new SignUpDeveloperCommand(signUpDeveloperResource.email(), signUpDeveloperResource.password(), signUpDeveloperResource.firstName(), signUpDeveloperResource.lastName());
    }
}
