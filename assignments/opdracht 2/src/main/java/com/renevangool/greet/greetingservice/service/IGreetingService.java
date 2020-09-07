package com.renevangool.greet.greetingservice.service;

import org.springframework.stereotype.Service;

@Service
public interface IGreetingService {
    String getGreeting();
}
