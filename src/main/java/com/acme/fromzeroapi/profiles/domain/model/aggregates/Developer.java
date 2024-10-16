package com.acme.fromzeroapi.profiles.domain.model.aggregates;

import com.acme.fromzeroapi.profiles.domain.model.commands.CreateDeveloperProfileCommand;
import com.acme.fromzeroapi.profiles.domain.model.valueObjects.ProfileId;
import com.acme.fromzeroapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Developer extends AuditableAbstractAggregateRoot<Developer> {

    @Embedded
    private final ProfileId profileId;

    @NotBlank
    @Setter
    private String firstName;

    @NotBlank
    @Setter
    private String lastName;

    private String email;

    @Setter
    private String description = "No description provided.";
    @Setter
    private String country = "No country provided.";
    @Setter
    private String phone = "999 999 999";
    @Setter
    private int completedProjects = 0;
    @Setter
    private String specialties = "No specialties provided.";
    @Setter
    private String profileImgUrl = "https://cdn-icons-png.flaticon.com/512/3237/3237472.png";

    private Long userId;

    public Developer(
            String firstName,
            String lastName,
            String description,
            String country,
            String phone,
            int completedProjects,
            String specialties,
            String profileImgUrl,
            Long userId
            ) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.country = country;
        this.phone = phone;
        this.completedProjects = completedProjects;
        this.specialties = specialties;
        this.profileImgUrl = profileImgUrl;
        this.userId = userId;
    }

    public Developer(CreateDeveloperProfileCommand command){
        this();
        this.firstName=command.firstName();
        this.lastName=command.lastName();
        this.email=command.email();
        this.description=command.description();
        this.country=command.country();
        this.phone=command.phone();
        this.completedProjects=command.completedProjects();
        this.specialties=command.specialties();
        this.profileImgUrl=command.profileImgUrl();
        this.userId=command.userId();
    }

    public Developer() {
        this.profileId = new ProfileId();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Developer developer)) return false;
        return getId() != null && getId().equals(developer.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }


    /*public String getProfileId() {
        return this.profileId.RecordId();
    }*/
}
