package com.acme.fromzeroapi.iam.application.internal.outboundservices.acl;

import com.acme.fromzeroapi.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalProfileService {
    private final ProfileContextFacade profileContextFacade;

    public ExternalProfileService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    public void createDeveloperProfile(String firstName, String lastName, String email){
        profileContextFacade.createDeveloperProfile(
                firstName,
                lastName,
                email,
                "No description provided.",
                "No country provided.",
                "No phone provided.",
                0,
                "No specialties provided.",
                "https://cdn-icons-png.flaticon.com/512/3237/3237472.png"
        );
    }

    public void createCompanyProfile(String companyName,String email){
        profileContextFacade.createCompanyProfile(
                companyName,
                email,
                "No description provided.",
                "No country provided.",
                "No phone provided.",
                "999 999 999",
                "No website provided.",
                "https://cdn-icons-png.flaticon.com/512/3237/3237472.png",
                "No sector provided."
        );
    }
}
