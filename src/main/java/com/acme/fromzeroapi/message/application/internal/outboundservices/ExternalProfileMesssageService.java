package com.acme.fromzeroapi.message.application.internal.outboundservices;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProfileMesssageService {
    private final ProfileContextFacade profileContextFacade;

    public ExternalProfileMesssageService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    public Optional<Company> getCompanyByProfileId(String profileId){
        return profileContextFacade.getCompanyByProfileId(profileId);
    }

    public Optional<Developer> getDeveloperByProfileId(String profileId){
        return profileContextFacade.getDeveloperByProfileId(profileId);
    }
}
