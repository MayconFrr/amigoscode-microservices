package io.github.mayconfrr.fraud;

import io.github.mayconfrr.clients.fraud.FraudCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@Slf4j
public record FraudCheckController(FraudCheckService fraudCheckService) {

    @GetMapping("{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Long customerId) {
        log.info("new fraud check request for customer {}", customerId);
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
