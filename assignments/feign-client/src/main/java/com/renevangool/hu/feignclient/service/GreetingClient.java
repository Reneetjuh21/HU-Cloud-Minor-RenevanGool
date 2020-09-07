package com.renevangool.hu.feignclient.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("user-service-client")
public interface GreetingClient {
    @RequestMapping("/user")
    String getUser();
}
