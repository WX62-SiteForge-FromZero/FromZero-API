package com.acme.fromzeroapi.message.domain.model.queries;

import com.acme.fromzeroapi.iam.domain.model.aggregates.User;

public record GetAllMessageByRecipientByIdQuery(User recipient) {

}
