package com.acme.fromzeroapi.shared.infrastructure.exceptionHandler;

import com.acme.fromzeroapi.shared.domain.exceptions.CompanyNotFoundException;
import com.acme.fromzeroapi.shared.domain.exceptions.DeveloperNotFoundException;
import com.acme.fromzeroapi.shared.interfaces.rest.resources.MessageResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

    @ExceptionHandler({CompanyNotFoundException.class, DeveloperNotFoundException.class})
    public ResponseEntity<MessageResource> handle(RuntimeException ex){
        LOGGER.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new MessageResource(ex.getMessage()));
    }
}
