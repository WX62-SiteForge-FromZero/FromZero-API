package com.acme.fromzeroapi.projects.domain.model.commands;

import com.acme.fromzeroapi.projects.domain.model.valueObjects.Frameworks;
import com.acme.fromzeroapi.projects.domain.model.valueObjects.ProgrammingLanguages;
import com.acme.fromzeroapi.projects.domain.model.valueObjects.ProjectType;

import java.util.Set;

public record CreateProjectCommand(
        String name,
        String description,
        Long companyId,
        Set<ProgrammingLanguages> languages,
        Set<Frameworks> frameworks,
        ProjectType type,
        String budget,
        String methodologies
) {

}
