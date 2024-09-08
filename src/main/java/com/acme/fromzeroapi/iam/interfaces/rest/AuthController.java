package com.acme.fromzeroapi.iam.interfaces.rest;

import com.acme.fromzeroapi.iam.domain.services.UserCommandService;
import com.acme.fromzeroapi.iam.interfaces.rest.resources.*;
import com.acme.fromzeroapi.iam.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/auth")
@Tag(name = "Auth", description = "Operations related to users")
public class AuthController {
    private final UserCommandService userCommandService;

    public AuthController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @Operation(summary = "Create Developer")
    @PostMapping("/register-developer")
    public ResponseEntity<UserResource> createDeveloper(@RequestBody SignUpDeveloperResource resource) {
        var command = DeveloperCommandFromSignUpDeveloperResourceAssembler.toCommandFromResource(resource);
        var user = userCommandService.handle(command);

        if (user.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
    @Operation(summary = "Create Company")
    @PostMapping("/register-company")
    public ResponseEntity<UserResource> createCompany(@RequestBody SignUpCompanyResource resource) {
        var registerCommand = CompanyCommandFromSignUpCompanyResourceAssembler.toCommandFromResource(resource);
        var user = userCommandService.handle(registerCommand);

        if (user.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
    @Operation(summary = "Create Support")
    @PostMapping("/register-support")
    public ResponseEntity<UserResource> createSupport(@RequestBody SignUpSupportResource resource) {
        var command = SupportCommandFromSignUpSupportResourceAssembler.toCommandFromResource(resource);
        var user = userCommandService.handle(command);

        if (user.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
    @Operation(summary = "sign in")
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource signInResource){
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);

        if(authenticatedUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var authenticatedUserResource = AuthenticatedUsedResourcerFromEntityAssembler.toResourceFromEntity(
                authenticatedUser.get().getLeft(),authenticatedUser.get().getRight());

        return ResponseEntity.ok(authenticatedUserResource);
    }
}
