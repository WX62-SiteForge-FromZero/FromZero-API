package com.acme.fromzeroapi.profiles.interfaces.rest;

import com.acme.fromzeroapi.profiles.domain.model.queries.*;
import com.acme.fromzeroapi.profiles.domain.services.ProfileCommandService;
import com.acme.fromzeroapi.profiles.domain.services.ProfileQueryService;
import com.acme.fromzeroapi.profiles.interfaces.rest.resources.*;
import com.acme.fromzeroapi.profiles.interfaces.rest.transform.CompanyProfileResourceFromEntityAssembler;
import com.acme.fromzeroapi.profiles.interfaces.rest.transform.DeveloperProfileResourceFromEntityAssembler;
import com.acme.fromzeroapi.profiles.interfaces.rest.transform.UpdateCompanyProfileCommandFromResourceAssembler;
import com.acme.fromzeroapi.profiles.interfaces.rest.transform.UpdateDeveloperProfileCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/profiles")
@Tag(name = "Profiles", description = "Operations related to profiles")
public class ProfileController {
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfileController(ProfileQueryService profileQueryService,
                             ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    @Operation(summary = "Get all developers")
    @GetMapping("/developers")
    public ResponseEntity<List<DeveloperProfileResource>> getAllDevelopers()
    {
        var getAllDevelopersQuery = new GetAllDevelopersQuery();
        var developers = profileQueryService.handle(getAllDevelopersQuery);
        if(developers.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        var developersResource = developers.stream()
                .map(DeveloperProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(developersResource);
    }

    @Operation(summary = "Get all companies")
    @GetMapping("/companies")
    public ResponseEntity<List<CompanyProfileResource>> getAllCompanies()
    {
        var getAllCompaniesQuery = new GetAllCompaniesQuery();
        var companies = profileQueryService.handle(getAllCompaniesQuery);
        if(companies.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        var companiesResource = companies.stream()
                .map(CompanyProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(companiesResource);
    }

    @Operation(summary = "Get Company Id and Record Id By Email")
    @GetMapping("/company-data/{email}")
    public ResponseEntity<CompanyDataResource> getCompanyDataByEmail(@PathVariable String email) {
        var company = profileQueryService.handle(new GetCompanyByEmailQuery(email));
        if (company.isEmpty())return ResponseEntity.notFound().build();
        var resource = new CompanyDataResource(company.get().getId(),company.get().getProfileId().RecordId());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Get Developer Id and Record Id By Email")
    @GetMapping("/developer-data/{email}")
    public ResponseEntity<DeveloperDataResource> getDeveloperDataByEmail(@PathVariable String email) {
        var developer = profileQueryService.handle(new GetDeveloperByEmailQuery(email));
        if (developer.isEmpty())return ResponseEntity.notFound().build();
        var resource = new DeveloperDataResource(developer.get().getId(),developer.get().getProfileId().RecordId());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Update developer profile")
    //@PutMapping("/developer/profile/{id}")
    @PutMapping("/developer/{id}")
    public ResponseEntity<DeveloperProfileResource> updateDeveloperProfile(@PathVariable Long id, @RequestBody UpdateDeveloperProfileResource resource) {
        var command = UpdateDeveloperProfileCommandFromResourceAssembler.toCommandFromResource(id,resource);
        var updatedDeveloper = profileCommandService.handle(command);
        if (updatedDeveloper.isEmpty()) return ResponseEntity.notFound().build();
        var developerResource = DeveloperProfileResourceFromEntityAssembler.toResourceFromEntity(updatedDeveloper.get());
        return ResponseEntity.ok(developerResource);
    }

    @Operation(summary = "Update company profile")
    //@PutMapping("/company/profile/{id}")
    @PutMapping("/company/{id}")
    public ResponseEntity<CompanyProfileResource> updateEnterpriseProfile(@PathVariable Long id, @RequestBody UpdateCompanyProfileResource resource) {
        var command = UpdateCompanyProfileCommandFromResourceAssembler.toCommandFromResource(id,resource);
        var updatedEnterprise = profileCommandService.handle(command);
        if (updatedEnterprise.isEmpty()) return ResponseEntity.notFound().build();
        var companyResource = CompanyProfileResourceFromEntityAssembler.toResourceFromEntity(updatedEnterprise.get());
        return ResponseEntity.ok(companyResource);
    }

    @Operation(summary = "Get Company Profile By Id or Record Id")
    @GetMapping("/company/{id}")
    public ResponseEntity<CompanyProfileResource> getCompanyProfile(@PathVariable String id){
        var company = profileQueryService.handle(new GetCompanyProfileByIdOrRecordIdQuery(id));
        if (company.isEmpty()) return ResponseEntity.notFound().build();
        var resource = CompanyProfileResourceFromEntityAssembler.toResourceFromEntity(company.get());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Get Developer Profile By Id or Record Id")
    @GetMapping("/developer/{id}")
    public ResponseEntity<DeveloperProfileResource> getDeveloperProfile(@PathVariable String id){
        var developer = profileQueryService.handle(new GetDeveloperProfileByIdOrRecordIdQuery(id));
        if (developer.isEmpty()) return ResponseEntity.notFound().build();
        var resource = DeveloperProfileResourceFromEntityAssembler.toResourceFromEntity(developer.get());
        return ResponseEntity.ok(resource);
    }
}
