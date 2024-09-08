package com.acme.fromzeroapi.projects.application.internal.commandServices;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Framework;
import com.acme.fromzeroapi.projects.domain.model.commands.SeedFrameworks;
import com.acme.fromzeroapi.projects.domain.model.valueObjects.Frameworks;
import com.acme.fromzeroapi.projects.domain.services.FrameworksCommandService;
import com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories.FrameworksRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class FrameworksCommandServiceImpl implements FrameworksCommandService {
    private final FrameworksRepository frameworksRepository;

    public FrameworksCommandServiceImpl(FrameworksRepository frameworksRepository) {
        this.frameworksRepository = frameworksRepository;
    }

    @Override
    public void handle(SeedFrameworks command) {
        Arrays.stream(Frameworks.values()).forEach(item->{
            if (!frameworksRepository.existsByName(item.name())){
                frameworksRepository.save(new Framework(item.name()));
            }
        });
    }
}
