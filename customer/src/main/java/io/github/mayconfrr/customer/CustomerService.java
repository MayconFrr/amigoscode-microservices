package io.github.mayconfrr.customer;

import io.github.mayconfrr.clients.fraud.FraudCheckResponse;
import io.github.mayconfrr.clients.fraud.FraudClient;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient) {

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

        // @TODO: send notification
    }
}
