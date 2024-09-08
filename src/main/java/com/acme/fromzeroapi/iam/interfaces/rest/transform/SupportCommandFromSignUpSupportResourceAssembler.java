package com.acme.fromzeroapi.iam.interfaces.rest.transform;

import com.acme.fromzeroapi.iam.domain.model.commands.SignUpSupportCommand;
import com.acme.fromzeroapi.iam.interfaces.rest.resources.SignUpSupportResource;

public class SupportCommandFromSignUpSupportResourceAssembler {
    public static SignUpSupportCommand toCommandFromResource(SignUpSupportResource signUpSupportResource) {
        return new SignUpSupportCommand(signUpSupportResource.mail(), signUpSupportResource.password());
    }
}
