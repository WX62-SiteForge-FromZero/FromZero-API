package com.acme.fromzeroapi.profiles.interfaces.rest;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.profiles.domain.model.commands.UpdateCompanyProfileCommand;
import com.acme.fromzeroapi.profiles.domain.model.commands.UpdateDeveloperProfileCommand;
import com.acme.fromzeroapi.profiles.domain.model.queries.*;
import com.acme.fromzeroapi.profiles.domain.services.ProfileCommandService;
import com.acme.fromzeroapi.profiles.domain.services.ProfileQueryService;
import com.acme.fromzeroapi.profiles.interfaces.rest.resources.CompanyProfileResource;
import com.acme.fromzeroapi.profiles.interfaces.rest.resources.DeveloperProfileResource;
import com.acme.fromzeroapi.profiles.interfaces.rest.transform.CompanyProfileResourceFromEntityAssembler;
import com.acme.fromzeroapi.profiles.interfaces.rest.transform.DeveloperProfileResourceFromEntityAssembler;
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
    public ResponseEntity<List<Developer>> getAllDevelopers() {
        return ResponseEntity.ok(profileQueryService.handle(new GetAllDevelopersAsyncQuery()));
    }

    @Operation(summary = "Get Developer Profile Id by email")
    @GetMapping(value = "/developer/{email}")
    public ResponseEntity<Long> getDeveloperProfileIdByEmail(@PathVariable String email) {
        var query = new GetDeveloperProfileIdByEmailQuery(email);
        var developer = profileQueryService.handle(query);
        if (developer.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(developer.get().getId());
    }

    @Operation(summary = "Get Company Profile Id by email")
    @GetMapping(value = "/company/{email}")
    public ResponseEntity<Long> getCompanyProfileIdByEmail(@PathVariable String email) {
        var query = new GetCompanyProfileIdByEmailQuery(email);
        var company = profileQueryService.handle(query);
        if (company.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(company.get().getId());
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
    @GetMapping(value = "/company/profile/{id}")
    public ResponseEntity<CompanyProfileResource> getCompanyProfile(@PathVariable Long id){
        var query = new GetCompanyByIdQuery(id);
        var company = profileQueryService.handle(query);
        if (company.isEmpty()) return ResponseEntity.notFound().build();
        var resource = CompanyProfileResourceFromEntityAssembler.toResourceFromEntity(company.get());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Update developer profile")
    @PutMapping("/developer/profile/{id}")
    public ResponseEntity<Developer> updateDeveloperProfile(@PathVariable Long id, @RequestBody UpdateDeveloperProfileCommand command) {

        if (!id.equals(command.id())) {
            throw new IllegalArgumentException("Path variable id doesn't match with request body id");
        }
        var updatedDeveloper = profileCommandService.handle(command);

        return ResponseEntity.ok(updatedDeveloper.get());
    }

    @Operation(summary = "Update company profile")
    @PutMapping("/company/profile/{id}")
    public ResponseEntity<Company> updateEnterpriseProfile(@PathVariable Long id, @RequestBody UpdateCompanyProfileCommand command) {

        if (!id.equals(command.id())) {
            throw new IllegalArgumentException("Path variable id doesn't match with request body id");
        }
        var updatedEnterprise = profileCommandService.handle(command);

        return ResponseEntity.ok(updatedEnterprise.get());
    }

}
