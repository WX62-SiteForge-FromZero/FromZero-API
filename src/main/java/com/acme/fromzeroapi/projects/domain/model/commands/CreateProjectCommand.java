package com.acme.fromzeroapi.projects.domain.model.commands;

//import com.acme.fromzeroapi.enterprise_branch_projects.domain.model.aggregates.Company;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.projects.domain.model.aggregates.Framework;
import com.acme.fromzeroapi.projects.domain.model.aggregates.ProgrammingLanguage;

import java.util.List;

public record CreateProjectCommand(
        String name, String description, Company company,
        List<ProgrammingLanguage> languages, List<Framework> frameworks,String type,
        String budget,String methodologies) {

}
