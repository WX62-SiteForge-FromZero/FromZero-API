package com.acme.fromzeroapi.payment.interfaces.rest;

import com.acme.fromzeroapi.payment.domain.model.queries.GetPaymentByProjectIdQuery;
import com.acme.fromzeroapi.payment.domain.model.queries.GetPaymentsByDeveloperIdQuery;
import com.acme.fromzeroapi.payment.domain.services.PaymentCommandService;
import com.acme.fromzeroapi.payment.domain.services.PaymentQueryService;
import com.acme.fromzeroapi.payment.interfaces.rest.resources.CompletePaymentResource;
import com.acme.fromzeroapi.payment.interfaces.rest.resources.PaymentResource;
import com.acme.fromzeroapi.payment.interfaces.rest.transform.CompletePaymentCommandFromResourceAssembler;
import com.acme.fromzeroapi.payment.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/api/payments",produces = APPLICATION_JSON_VALUE)
@Tag(name = "Payment", description = "Project Payments Management Endpoints")
public class PaymentController {
    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;

    public PaymentController(
            PaymentCommandService paymentCommandService,
            PaymentQueryService paymentQueryService
    ) {
        this.paymentCommandService = paymentCommandService;
        this.paymentQueryService = paymentQueryService;
    }

    @Operation(summary = "Complete Project Payment")
    @PatchMapping(value = "/project/{projectId}")
    public ResponseEntity<PaymentResource> completePayment(@RequestBody CompletePaymentResource resource, @PathVariable Long projectId) {
        var command = CompletePaymentCommandFromResourceAssembler.toCommandFromResource(resource, projectId);
        var payment = paymentCommandService.handle(command);
        if (payment.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(payment.get());
        return ResponseEntity.ok(paymentResource);
    }

    @Operation(summary = "Get Payment By Project Id")
    @GetMapping(value = "/project/{projectId}")
    public ResponseEntity<PaymentResource> getProjectPayment(@PathVariable Long projectId) {
        var payment = paymentQueryService.handle(new GetPaymentByProjectIdQuery(projectId));
        if (payment.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(payment.get());
        return ResponseEntity.ok(paymentResource);
    }

    @Operation(summary = "Get Payments By Developer Id")
    @GetMapping(value = "/developer/{developerId}")
    public ResponseEntity<List<PaymentResource>> getAllPaymentsByDeveloper(@PathVariable Long developerId) {
        var payments = paymentQueryService.handle(new GetPaymentsByDeveloperIdQuery(developerId));
        var paymentsResources = payments.stream()
                .map(PaymentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentsResources);
    }
}
