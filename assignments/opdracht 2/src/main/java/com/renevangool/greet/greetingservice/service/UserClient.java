package com.renevangool.greet.greetingservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("user-service-client")
public interface UserClient {
    @RequestMapping("/user")
    String getUser();
}
