package com.acme.fromzeroapi.iam.domain.model.commands;

public record SignUpSupportCommand(
        CreateUserCommand createUserCommand
) {
        public SignUpSupportCommand(String mail, String password) {
                this(new CreateUserCommand(mail, password, "S"));
        }

        public boolean isOfType(Class<?> type) {
                return type.isInstance(createUserCommand);
        }
}
