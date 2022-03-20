package io.github.mayconfrr.clients.notification;

public record NotificationRequest(String message,
                                  String toCustomerEmail,
                                  Long toCustomerId) {
}
