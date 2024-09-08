package com.acme.fromzeroapi.iam.domain.model.commands;

public record SignUpCompanyCommand(
        CreateUserCommand createUserCommand,
        String enterpriseName
) {
        public SignUpCompanyCommand(String mail, String password, String enterpriseName) {
                this(new CreateUserCommand(mail, password, "E"), enterpriseName);
        }

        public boolean isOfType(Class<?> type) {
                return type.isInstance(createUserCommand);
        }
}
