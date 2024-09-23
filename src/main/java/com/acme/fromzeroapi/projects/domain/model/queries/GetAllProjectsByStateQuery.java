package com.acme.fromzeroapi.projects.domain.model.queries;

import com.acme.fromzeroapi.projects.domain.model.valueObjects.ProjectState;

public record GetAllProjectsByStateQuery(ProjectState state) {

}
