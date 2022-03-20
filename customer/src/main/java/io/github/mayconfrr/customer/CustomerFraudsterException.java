package io.github.mayconfrr.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerFraudsterException extends RuntimeException {
    public CustomerFraudsterException(String message) {
        super(message);
    }
}
