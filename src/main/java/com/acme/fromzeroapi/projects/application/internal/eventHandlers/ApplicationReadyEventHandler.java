package com.acme.fromzeroapi.projects.application.internal.eventHandlers;

import com.acme.fromzeroapi.projects.domain.model.commands.SeedFrameworks;
import com.acme.fromzeroapi.projects.domain.model.commands.SeedProgrammingLanguages;
import com.acme.fromzeroapi.projects.domain.services.FrameworksCommandService;
import com.acme.fromzeroapi.projects.domain.services.ProgrammingLanguagesCommandService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ApplicationReadyEventHandler {
    private final ProgrammingLanguagesCommandService programmingLanguagesCommandService;
    private final FrameworksCommandService frameworksCommandService;

    public ApplicationReadyEventHandler(ProgrammingLanguagesCommandService programmingLanguagesCommandService,
                                        FrameworksCommandService frameworksCommandService) {
        this.programmingLanguagesCommandService = programmingLanguagesCommandService;
        this.frameworksCommandService = frameworksCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event){
        var seedProgrammingLanguages = new SeedProgrammingLanguages();
        var seedFrameworks = new SeedFrameworks();
        programmingLanguagesCommandService.handle(seedProgrammingLanguages);
        frameworksCommandService.handle(seedFrameworks);
    }
}
