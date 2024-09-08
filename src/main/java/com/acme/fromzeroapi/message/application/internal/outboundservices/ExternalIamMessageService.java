package com.acme.fromzeroapi.message.application.internal.outboundservices;

import com.acme.fromzeroapi.iam.domain.model.aggregates.User;
import com.acme.fromzeroapi.iam.interfaces.acl.IamContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalIamMessageService {
    private final IamContextFacade iamContextFacade;

    public ExternalIamMessageService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public User getUserByIdExternalService(Long userId){
        return this.iamContextFacade.getUserById(userId);
    }
}
