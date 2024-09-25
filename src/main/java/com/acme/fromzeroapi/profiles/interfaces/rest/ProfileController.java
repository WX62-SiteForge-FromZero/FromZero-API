package com.acme.fromzeroapi.profiles.interfaces.rest;

import com.acme.fromzeroapi.profiles.domain.model.queries.*;
import com.acme.fromzeroapi.profiles.domain.services.ProfileCommandService;
import com.acme.fromzeroapi.profiles.domain.services.ProfileQueryService;
import com.acme.fromzeroapi.profiles.interfaces.rest.resources.CompanyProfileResource;
import com.acme.fromzeroapi.profiles.interfaces.rest.resources.DeveloperProfileResource;
import com.acme.fromzeroapi.profiles.interfaces.rest.resources.UpdateCompanyProfileResource;
import com.acme.fromzeroapi.profiles.interfaces.rest.resources.UpdateDeveloperProfileResource;
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
        var getAllDevelopersQuery = new GetAllDevelopersAsyncQuery();
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

    @Operation(summary = "Get Developer Profile Id by email")
    @GetMapping(value = "/developer/{email}")
    public ResponseEntity<String> getDeveloperProfileIdByEmail(@PathVariable String email) {
        var query = new GetDeveloperProfileIdByEmailQuery(email);
        var developer = profileQueryService.handle(query);
        if(developer.isEmpty())ResponseEntity.badRequest().build();
        var profileId = developer.get().getProfileId();
        //var profileId = developer.map(value -> ResponseEntity.ok(value.getProfileId())).orElseGet(() -> ResponseEntity.notFound().build());
        return ResponseEntity.ok(profileId);
    }

    @Operation(summary = "Get Company Profile Id by email")
    @GetMapping(value = "/company/{email}")
    public ResponseEntity<String> getCompanyProfileIdByEmail(@PathVariable String email) {
        var query = new GetCompanyProfileIdByEmailQuery(email);
        var company = profileQueryService.handle(query);
        if (company.isEmpty())ResponseEntity.badRequest().build();
        /*var profileId = company.map(value ->
                ResponseEntity.ok(value.getProfileId())
        ).orElseGet(() -> ResponseEntity.notFound().build());*/
        var profileId = company.get().getProfileId();
        return ResponseEntity.ok(profileId);
        //return ResponseEntity.ok(profileId.toString());
    }

    @Operation(summary = "Get Developer Profile By Id")
    @GetMapping(value = "/developer/profile/{id}")
    public ResponseEntity<DeveloperProfileResource> getDeveloperProfile(@PathVariable Long id){
        var query = new GetDeveloperByIdQuery(id);
        var developer = profileQueryService.handle(query);
        if (developer.isEmpty()) return ResponseEntity.notFound().build();
        var resource = DeveloperProfileResourceFromEntityAssembler.toResourceFromEntity(developer.get());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Get Company Profile By Id")
    @GetMapping(value = "/company/id/{id}")
    public ResponseEntity<CompanyProfileResource> getCompanyProfile(@PathVariable Long id){
        var query = new GetCompanyByIdQuery(id);
        var company = profileQueryService.handle(query);
        if (company.isEmpty()) return ResponseEntity.notFound().build();
        var resource = CompanyProfileResourceFromEntityAssembler.toResourceFromEntity(company.get());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Update developer profile")
    @PutMapping("/developer/profile/{id}")
    public ResponseEntity<DeveloperProfileResource> updateDeveloperProfile(@PathVariable Long id, @RequestBody UpdateDeveloperProfileResource resource) {

        /*if (!id.equals(command.id())) {
            throw new IllegalArgumentException("Path variable id doesn't match with request body id");
        }*/
        var command = UpdateDeveloperProfileCommandFromResourceAssembler.toCommandFromResource(id,resource);
        var updatedDeveloper = profileCommandService.handle(command);
        if (updatedDeveloper.isEmpty()) return ResponseEntity.notFound().build();
        var developerResource = DeveloperProfileResourceFromEntityAssembler.toResourceFromEntity(updatedDeveloper.get());
        return ResponseEntity.ok(developerResource);
    }

    @Operation(summary = "Update company profile")
    @PutMapping("/company/profile/{id}")
    public ResponseEntity<CompanyProfileResource> updateEnterpriseProfile(@PathVariable Long id, @RequestBody UpdateCompanyProfileResource resource) {

        /*if (!id.equals(command.id())) {
            throw new IllegalArgumentException("Path variable id doesn't match with request body id");
        }*/
        var command = UpdateCompanyProfileCommandFromResourceAssembler.toCommandFromResource(id,resource);
        var updatedEnterprise = profileCommandService.handle(command);
        if (updatedEnterprise.isEmpty()) return ResponseEntity.notFound().build();
        var companyResource = CompanyProfileResourceFromEntityAssembler.toResourceFromEntity(updatedEnterprise.get());
        return ResponseEntity.ok(companyResource);
    }

    @Operation(summary = "Get Company By Profile Id")
    @GetMapping(value = "/company/profileId/{profileId}")
    public ResponseEntity<CompanyProfileResource> getCompanyByProfileId(@PathVariable String profileId){
        var query = new GetCompanyByProfileIdQuery(profileId);
        var company = profileQueryService.handle(query);
        if (company.isEmpty()) return ResponseEntity.notFound().build();
        var resource = CompanyProfileResourceFromEntityAssembler.toResourceFromEntity(company.get());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Get Developer By Profile Id")
    @GetMapping(value = "/developer/profileId/{profileId}")
    public ResponseEntity<DeveloperProfileResource> getDeveloperByProfileId(@PathVariable String profileId){
        var query = new GetDeveloperByProfileIdQuery(profileId);
        var developer = profileQueryService.handle(query);
        if (developer.isEmpty()) return ResponseEntity.notFound().build();
        var resource = DeveloperProfileResourceFromEntityAssembler.toResourceFromEntity(developer.get());
        return ResponseEntity.ok(resource);
    }
}
