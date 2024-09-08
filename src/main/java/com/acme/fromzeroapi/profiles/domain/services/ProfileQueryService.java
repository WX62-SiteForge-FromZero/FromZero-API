package com.acme.fromzeroapi.profiles.domain.services;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.profiles.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    List<Developer> handle(GetAllDevelopersAsyncQuery query);
    Optional<Developer> handle(GetDeveloperByIdQuery query);
    Optional<Developer> handle(GetDeveloperProfileIdByEmailQuery query);
    Optional<Company> handle(GetCompanyProfileIdByEmailQuery query);
    Optional<Company> handle(GetCompanyByIdQuery query);
}
