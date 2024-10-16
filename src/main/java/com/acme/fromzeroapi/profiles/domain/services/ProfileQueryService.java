package com.acme.fromzeroapi.profiles.domain.services;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.profiles.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    List<Developer> handle(GetAllDevelopersQuery query);
    List<Company> handle(GetAllCompaniesQuery query);
    Optional<Developer> handle(GetDeveloperByIdQuery query);
    Optional<Company> handle(GetCompanyByIdQuery query);
    Optional<Company> handle(GetCompanyByProfileIdQuery query);
    Optional<Developer> handle(GetDeveloperByProfileIdQuery query);

    Optional<Company> handle(GetCompanyProfileByIdOrRecordIdQuery query);
    Optional<Developer> handle(GetDeveloperProfileByIdOrRecordIdQuery query);
    Optional<Company> handle(GetCompanyByEmailQuery query);
    Optional<Developer> handle(GetDeveloperByEmailQuery query);
}
