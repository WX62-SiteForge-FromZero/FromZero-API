package com.acme.fromzeroapi.profiles.domain.model.commands;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;

public record UpdateDeveloperCompletedProjectsCommand(Developer developer, int addProject) {
}
