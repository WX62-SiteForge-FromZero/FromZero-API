package com.acme.fromzeroapi.iam.domain.services;

import com.acme.fromzeroapi.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
