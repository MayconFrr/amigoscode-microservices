package io.github.mayconfrr.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "io.github.mayconfrr.customer",
                "io.github.mayconfrr.amqp"
        }
)
@EnableEurekaClient
@EnableFeignClients(basePackages = "io.github.mayconfrr.clients")
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
