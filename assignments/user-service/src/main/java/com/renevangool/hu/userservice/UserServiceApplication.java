package com.renevangool.hu.userservice;

import com.renevangool.hu.userservice.controller.UserController;
import com.renevangool.hu.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableCircuitBreaker
@RestController
public class UserServiceApplication implements UserController {

	private final IUserService userService;

	@Autowired
	public UserServiceApplication(IUserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public String getUser() {
		return this.userService.getUser();
	}
}
