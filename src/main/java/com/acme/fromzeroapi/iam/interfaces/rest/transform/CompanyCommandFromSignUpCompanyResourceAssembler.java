package com.acme.fromzeroapi.iam.interfaces.rest.transform;

import com.acme.fromzeroapi.iam.domain.model.commands.SignUpCompanyCommand;
import com.acme.fromzeroapi.iam.interfaces.rest.resources.SignUpCompanyResource;

public class CompanyCommandFromSignUpCompanyResourceAssembler {
    public static SignUpCompanyCommand toCommandFromResource(SignUpCompanyResource signUpEnterpriseResource) {
        return new SignUpCompanyCommand(signUpEnterpriseResource.mail(), signUpEnterpriseResource.password(), signUpEnterpriseResource.companyName());
    }
}
