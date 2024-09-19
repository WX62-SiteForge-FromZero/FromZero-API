package com.acme.fromzeroapi.projects.interfaces.rest.resources;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;

import java.util.List;
import java.util.Set;

public record UpdateProjectCandidatesListResource(
        String name,
        Set<Developer> candidates
) {
}
