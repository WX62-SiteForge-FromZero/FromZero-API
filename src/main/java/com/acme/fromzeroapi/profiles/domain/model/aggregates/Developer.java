package com.acme.fromzeroapi.profiles.domain.model.aggregates;

import com.acme.fromzeroapi.iam.domain.model.aggregates.User;
import com.acme.fromzeroapi.profiles.domain.model.commands.CreateDeveloperProfileCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    /*@OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;*/

    public Developer(
            //User user,
            String firstName,
            String lastName,
            String description,
            String country,
            String phone,
            int completedProjects,
            String specialties,
            String profileImgUrl) {
        //this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.country = country;
        this.phone = phone;
        this.completedProjects = completedProjects;
        this.specialties = specialties;
        this.profileImgUrl = profileImgUrl;
    }

    public Developer(CreateDeveloperProfileCommand command){
        this.firstName=command.firstName();
        this.lastName=command.lastName();
        this.email=command.email();
        this.description=command.description();
        this.country=command.country();
        this.phone=command.phone();
        this.completedProjects=command.completedProjects();
        this.specialties=command.specialties();
        this.profileImgUrl=command.profileImgUrl();
    }

    public Developer() {

    }
}
