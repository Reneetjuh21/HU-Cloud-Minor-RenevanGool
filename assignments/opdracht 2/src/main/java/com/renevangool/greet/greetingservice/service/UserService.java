package com.renevangool.greet.greetingservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService implements IUserService {
    private final UserClient userClient;

    @Autowired
    public UserService(
            UserClient userClient
    ) {
        this.userClient = userClient;
    }

    @HystrixCommand(fallbackMethod = "defaultUserName")
    @Override
    public String getUserName() {
        return this.userClient.getUser();
    }

    public String defaultUserName() {
        return "Giel Beelen";
    }
}
