package com.acme.fromzeroapi.profiles.domain.model.aggregates;

import com.acme.fromzeroapi.profiles.domain.model.commands.CreateCompanyProfileCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Setter
    private String companyName;

    private String email;

    @Setter
    private String description = "No description provided.";
    @Setter
    private String country = "No country provided.";
    @Setter
    private String ruc = "No RUC provided.";
    @Setter
    private String phone = "No phone provided.";
    @Setter
    private String website = "No website provided.";
    @Setter
    private String profileImgUrl = "https://cdn-icons-png.flaticon.com/512/3237/3237472.png";
    @Setter
    private String sector = "No sector provided.";

    /*@OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;*/

    public Company(
            //User user,
            String companyName,
            String description,
            String country,
            String ruc,
            String phone,
            String website,
            String profileImgUrl,
            String sector) {
        //this.user = user;
        this.companyName = companyName;
        this.description = description;
        this.country = country;
        this.ruc = ruc;
        this.phone = phone;
        this.website = website;
        this.profileImgUrl = profileImgUrl;
        this.sector = sector;
    }

    public Company(CreateCompanyProfileCommand command){
        this.companyName =command.companyName();
        this.email=command.email();
        this.description=command.description();
        this.country=command.country();
        this.ruc=command.ruc();
        this.phone=command.phone();
        this.website=command.website();
        this.profileImgUrl=command.profileImgUrl();
        this.sector=command.sector();
    }

    public Company() {

    }
}
