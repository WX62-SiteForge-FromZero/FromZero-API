package com.acme.fromzeroapi.iam.application.internal.commandservices;

import com.acme.fromzeroapi.iam.application.internal.outboundservices.acl.ExternalProfileService;
import com.acme.fromzeroapi.iam.application.internal.outboundservices.hashing.HashingService;
import com.acme.fromzeroapi.iam.application.internal.outboundservices.tokens.TokenService;
import com.acme.fromzeroapi.iam.domain.model.aggregates.User;
import com.acme.fromzeroapi.iam.domain.model.commands.*;
import com.acme.fromzeroapi.iam.domain.services.UserCommandService;
import com.acme.fromzeroapi.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final ExternalProfileService externalProfileService;
    private final TokenService tokenService;
    private final HashingService hashingService;

    public UserCommandServiceImpl(UserRepository userRepository,
                                  ExternalProfileService externalProfileService,
                                  TokenService tokenService,
                                  HashingService hashingService) {
        this.userRepository = userRepository;
        this.externalProfileService = externalProfileService;
        this.tokenService = tokenService;
        this.hashingService = hashingService;
    }

    @Override
    public Optional<User> handle(SignUpDeveloperCommand command) {
        String email = command.createUserCommand().email();
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        });

        var createUserCommand = new CreateUserCommand(
                email,
                hashingService.encode(command.createUserCommand().password()),
                command.createUserCommand().userType()
        );
        var user = new User(createUserCommand);

        userRepository.save(user);

        externalProfileService.createDeveloperProfile(
                command.firstName(), command.lastName(), command.createUserCommand().email());

        return Optional.of(user);
    }

    @Override
    public Optional<User> handle(SignUpCompanyCommand command) {
        var email = command.createUserCommand().email();
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        });
        var createUserCommand = new CreateUserCommand(
                email,
                hashingService.encode(command.createUserCommand().password()),
                command.createUserCommand().userType());
        var user = new User(createUserCommand);

        userRepository.save(user);

        externalProfileService.createCompanyProfile(
                command.enterpriseName(),command.createUserCommand().email());

        return Optional.of(user);
    }

    @Override
    public Optional<User> handle(SignUpSupportCommand command) {
        String email = command.createUserCommand().email();

        if (!email.equals("fromzero@support.com")) {
            throw new IllegalArgumentException("Invalid email. Support accounts can only be created for 'fromzero@support.com'");
        }

        userRepository.findByEmail(email).ifPresent(user -> {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        });

        User user = new User(new CreateUserCommand(
                email,
                hashingService.encode(command.createUserCommand().password()),
                command.createUserCommand().userType()
        ));
        userRepository.save(user);

        return Optional.of(user);
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        if (!hashingService.matches(command.password(), user.get().getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        var token = tokenService.generateToken(user.get().getEmail());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }
}

