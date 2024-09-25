package com.acme.fromzeroapi.profiles.interfaces.acl;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.profiles.domain.model.commands.CreateCompanyProfileCommand;
import com.acme.fromzeroapi.profiles.domain.model.commands.CreateDeveloperProfileCommand;
import com.acme.fromzeroapi.profiles.domain.model.commands.UpdateDeveloperCompletedProjectsCommand;
import com.acme.fromzeroapi.profiles.domain.model.queries.GetCompanyByIdQuery;
import com.acme.fromzeroapi.profiles.domain.model.queries.GetCompanyByProfileIdQuery;
import com.acme.fromzeroapi.profiles.domain.model.queries.GetDeveloperByIdQuery;
import com.acme.fromzeroapi.profiles.domain.model.queries.GetDeveloperByProfileIdQuery;
import com.acme.fromzeroapi.profiles.domain.services.ProfileCommandService;
import com.acme.fromzeroapi.profiles.domain.services.ProfileQueryService;
//import com.acme.fromzeroapi.developer_branch_projects.domain.model.queries.GetDeveloperByIdQuery;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileContextFacade {
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfileContextFacade(ProfileQueryService profileQueryService,
                                ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    public void createDeveloperProfile(
            String firstName,
            String lastName,
            String email,
            String description,
            String country,
            String phone,
            int completedProjects,
            String specialties,
            String profileImgUrl,
            Long userId
    ){
        var command = new CreateDeveloperProfileCommand(
                firstName,
                lastName,
                email,
                description,
                country,
                phone,
                completedProjects,
                specialties,
                profileImgUrl,
                userId
        );
        profileCommandService.handle(command);
    }

    public void createCompanyProfile(
            String companyName,
            String email,
            String description,
            String country,
            String ruc,
            String phone,
            String website,
            String profileImgUrl,
            String sector,
            Long userId
    ){
        var command = new CreateCompanyProfileCommand(
                companyName,
                email,
                description,
                country,
                ruc,
                phone,
                website,
                profileImgUrl,
                sector,
                userId
        );
        profileCommandService.handle(command);
    }

    public Optional<Developer> getDeveloperById(Long id){
        return profileQueryService.handle(new GetDeveloperByIdQuery(id));
    }

    public Optional<Company> getCompanyById(Long id){
        return profileQueryService.handle(new GetCompanyByIdQuery(id));
    }

    public void updateDeveloperCompletedProjects(Long developerId){

        var command = new UpdateDeveloperCompletedProjectsCommand(developerId);
        var updatedDeveloper = this.profileCommandService.handle(command);
    }

    public Optional<Developer> getDeveloperByProfileId(String profileId){
        return profileQueryService.handle(new GetDeveloperByProfileIdQuery(profileId));
    }

    public Optional<Company> getCompanyByProfileId(String profileId){
        return profileQueryService.handle(new GetCompanyByProfileIdQuery(profileId));
    }
}
