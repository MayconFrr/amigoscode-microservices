package io.github.mayconfrr.customer;

import io.github.mayconfrr.amqp.RabbitMQMessageProducer;
import io.github.mayconfrr.clients.fraud.FraudCheckResponse;
import io.github.mayconfrr.clients.fraud.FraudClient;
import io.github.mayconfrr.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              FraudClient fraudClient,
                              RabbitMQMessageProducer messageProducer) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .build();

        // @TODO: check if email valid
        // @TODO: check if email not taken

        customerRepository.save(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new CustomerFraudsterException("Customer " + customer.getFirstname() + " " + customer.getLastname() + " is fraudulent");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                MessageFormat.format("Welcome, {0}!", customer.getFirstname()),
                customer.getEmail(),
                customer.getId()
        );

        messageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }
}
