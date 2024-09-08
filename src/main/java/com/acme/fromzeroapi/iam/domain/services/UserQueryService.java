package com.acme.fromzeroapi.iam.domain.services;

import com.acme.fromzeroapi.iam.domain.model.aggregates.User;
import com.acme.fromzeroapi.iam.domain.model.queries.GetAllUsersQuery;
import com.acme.fromzeroapi.iam.domain.model.queries.GetUserByEmailQuery;
import com.acme.fromzeroapi.iam.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByEmailQuery query);

}
