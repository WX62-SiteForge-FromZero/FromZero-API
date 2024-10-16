package com.acme.fromzeroapi.payment.domain.model.valueObjects;

import jakarta.persistence.Embeddable;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;

@Embeddable
public record Card(String cardNumber, LocalDate expirationDate, String cvv) {
    public Card() {
        this(Strings.EMPTY,null,Strings.EMPTY);
    }
}
