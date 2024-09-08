package com.acme.fromzeroapi.projects.application.internal.outboundServices.acl;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalProfileProjectService {
    private final ProfileContextFacade profileContextFacade;

    public ExternalProfileProjectService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    public Developer getDeveloperById(Long developerId){
        return profileContextFacade.getDeveloperById(developerId);
    }

    public Company getCompanyById(Long companyId){
        return profileContextFacade.getCompanyById(companyId);
    }
}
