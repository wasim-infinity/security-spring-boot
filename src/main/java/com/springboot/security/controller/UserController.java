package com.springboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.request.UserDataRequest;
import com.springboot.security.response.ResponseData;
import com.springboot.security.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/create-account")
	public ResponseEntity<ResponseData> createAccount(@RequestBody UserDataRequest userDataRequest) {

		return userService.createAccount(userDataRequest);
	}


}
