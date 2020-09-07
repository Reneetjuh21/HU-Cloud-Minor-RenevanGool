package com.renevangool.greet.greetingservice;

import com.renevangool.greet.greetingservice.controller.GreetingController;
import com.renevangool.greet.greetingservice.service.IGreetingService;
import com.renevangool.greet.greetingservice.service.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients
@RestController
public class GreetingServiceApplication implements GreetingController {

    private final IGreetingService greetingService;

    @Value("${user.role}")
    private String role;

    @Autowired
    public GreetingServiceApplication(
            IGreetingService greetingService
    ) {
        this.greetingService = greetingService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GreetingServiceApplication.class, args);
    }

    @GetMapping
    public String getGreeting() {
//        return this.greetingService.getGreeting();
        return role;
    }
}
