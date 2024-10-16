package com.acme.fromzeroapi.projects.domain.model.valueObjects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public record ProjectBudget(
        Double budget,
        @Enumerated(EnumType.STRING)
        ProjectCurrency currency
) {
    public String projectBudgetToString(){
        return budget+" "+currency;
    }
}
