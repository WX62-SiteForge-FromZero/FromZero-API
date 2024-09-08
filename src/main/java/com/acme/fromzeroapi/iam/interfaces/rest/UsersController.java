package com.acme.fromzeroapi.iam.interfaces.rest;

import com.acme.fromzeroapi.iam.domain.model.queries.GetUserByEmailQuery;
import com.acme.fromzeroapi.iam.domain.services.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersController {

    private final UserQueryService userQueryService;

    public UsersController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @Operation(summary = "Get user id by email")
    @GetMapping("/email/{email}")
    public ResponseEntity<Long> getUserIdByEmail(@PathVariable String email) {
        var query=new GetUserByEmailQuery(email);
        var user = this.userQueryService.handle(query);
        if(user.isEmpty())return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user.get().getId());
    }
}
