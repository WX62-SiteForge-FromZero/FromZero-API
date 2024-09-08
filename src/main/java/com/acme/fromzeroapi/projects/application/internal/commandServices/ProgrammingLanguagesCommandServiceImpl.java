package com.acme.fromzeroapi.projects.application.internal.commandServices;

import com.acme.fromzeroapi.projects.domain.model.aggregates.ProgrammingLanguage;
import com.acme.fromzeroapi.projects.domain.model.commands.SeedProgrammingLanguages;
import com.acme.fromzeroapi.projects.domain.model.valueObjects.ProgrammingLanguages;
import com.acme.fromzeroapi.projects.domain.services.ProgrammingLanguagesCommandService;
import com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories.ProgrammingLanguagesRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProgrammingLanguagesCommandServiceImpl implements ProgrammingLanguagesCommandService {
    private final ProgrammingLanguagesRepository programmingLanguagesRepository;

    public ProgrammingLanguagesCommandServiceImpl(ProgrammingLanguagesRepository programmingLanguagesRepository) {
        this.programmingLanguagesRepository = programmingLanguagesRepository;
    }

    @Override
    public void handle(SeedProgrammingLanguages command) {
        Arrays.stream(ProgrammingLanguages.values()).forEach(item->{
            if (!programmingLanguagesRepository.existsByName(item.name())){
                programmingLanguagesRepository.save(new ProgrammingLanguage(item.name()));
            }
        });
    }
}
