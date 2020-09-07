package com.renevangool.greet.greetingservice.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService implements IGreetingService {

    private final IUserService userService;

    public GreetingService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public String getGreeting() {
        return "Greetings, " + this.userService.getUserName();
    }
}
