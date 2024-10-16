package com.acme.fromzeroapi.payment.domain.services;

import com.acme.fromzeroapi.payment.domain.model.aggregates.Payment;
import com.acme.fromzeroapi.payment.domain.model.queries.GetPaymentByProjectIdQuery;
import com.acme.fromzeroapi.payment.domain.model.queries.GetPaymentsByDeveloperIdQuery;

import java.util.List;
import java.util.Optional;

public interface PaymentQueryService {
    Optional<Payment> handle(GetPaymentByProjectIdQuery query);
    List<Payment> handle(GetPaymentsByDeveloperIdQuery query);
}
