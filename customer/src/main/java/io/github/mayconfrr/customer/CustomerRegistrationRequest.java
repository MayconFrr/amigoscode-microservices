package io.github.mayconfrr.customer;

public record CustomerRegistrationRequest(
        String firstname,
        String lastname,
        String email) {
}
