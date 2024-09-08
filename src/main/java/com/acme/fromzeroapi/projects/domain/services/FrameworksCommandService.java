package com.acme.fromzeroapi.projects.domain.services;

import com.acme.fromzeroapi.projects.domain.model.commands.SeedFrameworks;

public interface FrameworksCommandService {
    void handle(SeedFrameworks command);
}
