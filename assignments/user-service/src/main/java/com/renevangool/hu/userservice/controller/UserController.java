package com.renevangool.hu.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user")
public interface UserController {

    @GetMapping
    String getUser();
}
