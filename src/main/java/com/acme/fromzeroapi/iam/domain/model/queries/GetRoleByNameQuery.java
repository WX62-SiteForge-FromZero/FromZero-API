package com.acme.fromzeroapi.iam.domain.model.queries;

import com.acme.fromzeroapi.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
