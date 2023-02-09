package com.springboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.request.LoginRequest;
import com.springboot.security.request.UserDataRequest;
import com.springboot.security.response.ResponseData;
import com.springboot.security.service.UserService;

@RestController
public class SecurityController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/create-account")
	public ResponseEntity<ResponseData> createAccount(@RequestBody UserDataRequest userDataRequest) {

		return userService.createAccount(userDataRequest);
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseData> generateToken(@RequestBody LoginRequest loginRequest) throws Exception {

		return userService.generateToken(loginRequest);

	}

	@GetMapping("/home")
	public String home() {

		return "Welcome to home";

	}

	@GetMapping("/user")
	public String user(@RequestHeader String token) {

		return "Welcome User";

	}

	@GetMapping("/admin")
	public String admin(@RequestHeader String token) {

		return "Welcome Admin";

	}

}
