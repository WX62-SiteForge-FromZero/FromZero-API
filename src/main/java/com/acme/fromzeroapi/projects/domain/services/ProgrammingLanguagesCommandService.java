package com.acme.fromzeroapi.projects.domain.services;

import com.acme.fromzeroapi.projects.domain.model.commands.SeedProgrammingLanguages;

public interface ProgrammingLanguagesCommandService {
    void handle(SeedProgrammingLanguages command);
}
