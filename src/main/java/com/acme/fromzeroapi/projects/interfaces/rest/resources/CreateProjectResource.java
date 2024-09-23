package com.acme.fromzeroapi.projects.interfaces.rest.resources;

import com.acme.fromzeroapi.projects.domain.model.valueObjects.Frameworks;
import com.acme.fromzeroapi.projects.domain.model.valueObjects.ProgrammingLanguages;
import com.acme.fromzeroapi.projects.domain.model.valueObjects.ProjectType;

import java.util.List;
import java.util.Set;

public record CreateProjectResource(
        String name,
        String description,
        Long ownerId,
        Set<ProgrammingLanguages> languages,
        Set<Frameworks> frameworks,
        ProjectType type,
        String budget,
        String methodologies
) {

}
