package com.springboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.request.LoginRequest;
import com.springboot.security.response.ResponseData;
import com.springboot.security.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<ResponseData> generateToken(@RequestBody LoginRequest loginRequest) throws Exception {

		return loginService.generateToken(loginRequest);

	}

}
