package com.acme.fromzeroapi.iam.domain.model.aggregates;

import com.acme.fromzeroapi.iam.domain.model.entities.Role;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public User() {

    }

    public User(String email, String password, List<Role> roles){
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>();
        addRoles(roles);
    }


    public User addRoles(List<Role> roles) {
        //var validatedRoles = Role.validateRoleSet(roles);
        this.roles.addAll(roles);
        return this;
    }
}
