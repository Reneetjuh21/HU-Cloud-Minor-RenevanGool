package com.renevangool.hu.userservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.renevangool.hu.userservice.model.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService implements IUserService{

    private final RestTemplate restTemplate;

    public UserService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @HystrixCommand(fallbackMethod = "defaultUser")
    @Override
    public String getUser() {
        final int randomInteger = (int)(Math.random() * 10 + 1);
        final String url = "https://jsonplaceholder.typicode.com/users/"+randomInteger;
        return this.restTemplate.getForObject(url, User.class).getName();
    }

    private String defaultUser() {
        return "Giel Beelen";
    }
}
