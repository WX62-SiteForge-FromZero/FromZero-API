package com.acme.fromzeroapi.profiles.interfaces.acl;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.profiles.domain.model.commands.CreateCompanyProfileCommand;
import com.acme.fromzeroapi.profiles.domain.model.commands.CreateDeveloperProfileCommand;
import com.acme.fromzeroapi.profiles.domain.model.commands.UpdateDeveloperCompletedProjectsCommand;
import com.acme.fromzeroapi.profiles.domain.model.queries.GetCompanyByIdQuery;
import com.acme.fromzeroapi.profiles.domain.model.queries.GetDeveloperByIdQuery;
import com.acme.fromzeroapi.profiles.domain.services.ProfileCommandService;
import com.acme.fromzeroapi.profiles.domain.services.ProfileQueryService;
//import com.acme.fromzeroapi.developer_branch_projects.domain.model.queries.GetDeveloperByIdQuery;
import org.springframework.stereotype.Service;

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
            String profileImgUrl
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
                profileImgUrl
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
            String sector
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
                sector
        );
        profileCommandService.handle(command);
    }

    public Developer getDeveloperById(Long id){
        var query = new GetDeveloperByIdQuery(id);
        var developer = profileQueryService.handle(query);
        return developer.orElse(null);
    }

    public Company getCompanyById(Long id){
        var query = new GetCompanyByIdQuery(id);
        var company = profileQueryService.handle(query);
        return company.orElse(null);
    }

    public void updateDeveloperCompletedProjects(Long developerId){
        var developer = profileQueryService.handle(new GetDeveloperByIdQuery(developerId));
        if (developer.isEmpty())return;

        var count = developer.get().getCompletedProjects();
        var command = new UpdateDeveloperCompletedProjectsCommand(developer.get(),count+1);
        var updatedDeveloper = this.profileCommandService.handle(command);

    }
}
