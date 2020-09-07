package com.renevangool.greet.greetingservice.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("greet")
public interface GreetingController {
    @GetMapping
    String getGreeting();
}
