package com.acme.fromzeroapi.payment.application.internal.queryServices;

import com.acme.fromzeroapi.payment.application.internal.outboundservices.acl.ExternalProfilePaymentService;
import com.acme.fromzeroapi.payment.application.internal.outboundservices.acl.ExternalProjectPaymentService;
import com.acme.fromzeroapi.payment.domain.model.aggregates.Payment;
import com.acme.fromzeroapi.payment.domain.model.queries.GetPaymentByProjectIdQuery;
import com.acme.fromzeroapi.payment.domain.model.queries.GetPaymentsByDeveloperIdQuery;
import com.acme.fromzeroapi.payment.domain.services.PaymentQueryService;
import com.acme.fromzeroapi.payment.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {
    private final PaymentRepository paymentRepository;
    private final ExternalProjectPaymentService externalProjectPaymentService;
    private final ExternalProfilePaymentService externalProfilePaymentService;

    public PaymentQueryServiceImpl(
            PaymentRepository paymentRepository,
            ExternalProjectPaymentService externalProjectPaymentService,
            ExternalProfilePaymentService externalProfilePaymentService
    ) {
        this.paymentRepository = paymentRepository;
        this.externalProjectPaymentService = externalProjectPaymentService;
        this.externalProfilePaymentService = externalProfilePaymentService;
    }

    @Override
    public Optional<Payment> handle(GetPaymentByProjectIdQuery query) {
        var project = externalProjectPaymentService.fetchProject(query.projectId());
        if (project.isEmpty()){
            return Optional.empty();
        }
        return paymentRepository.findByProject(project.get());
    }

    @Override
    public List<Payment> handle(GetPaymentsByDeveloperIdQuery query) {
        var developer = externalProfilePaymentService.fetchDeveloper(query.developerId());
        if (developer.isEmpty()){
            return List.of();
        }
        return paymentRepository.findAllByDeveloper(developer.get());
    }
}
