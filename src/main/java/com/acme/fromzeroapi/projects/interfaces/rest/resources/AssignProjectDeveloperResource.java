package com.acme.fromzeroapi.projects.interfaces.rest.resources;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.projects.domain.model.valueObjects.ProjectState;
import java.util.Set;

public record AssignProjectDeveloperResource(
        String name,
        ProjectState state,
        Developer developer,
        Set<Developer> candidates
) {
}
