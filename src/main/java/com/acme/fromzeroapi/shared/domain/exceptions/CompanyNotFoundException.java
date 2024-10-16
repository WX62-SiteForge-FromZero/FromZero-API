package com.acme.fromzeroapi.shared.domain.exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(String companyRecordId) {
        super("Company with record id " + companyRecordId + " not found");
    }
}
