package com.acme.fromzeroapi.projects.domain.model.queries;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;

public record GetAllProjectsByCompanyIdQuery(Company company) {
}
