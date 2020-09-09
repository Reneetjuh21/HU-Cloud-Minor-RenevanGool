package com.renevangool.greet.greetingservice.controller;

import com.renevangool.greet.greetingservice.model.DummyEmployee;
import com.renevangool.greet.greetingservice.service.DummyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dummy")
public class DummyController {
    private final DummyService dummyService;

    public DummyController() {
        dummyService = new DummyService();
    }

    @GetMapping
    public DummyEmployee getEmployee() throws Exception {
        return dummyService.getEmployee();
    }
}
