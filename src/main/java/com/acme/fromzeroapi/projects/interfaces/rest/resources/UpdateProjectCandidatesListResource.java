package com.acme.fromzeroapi.projects.interfaces.rest.resources;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;

import java.util.List;

public record UpdateProjectCandidatesListResource(String name, String description, List<Developer> candidates) {
}
