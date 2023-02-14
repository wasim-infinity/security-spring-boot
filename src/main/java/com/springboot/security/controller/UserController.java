package com.springboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.request.UserDataRequest;
import com.springboot.security.response.ResponseData;
import com.springboot.security.response.UserResponse;
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

	@GetMapping("/get-mapped-users")
	public ResponseEntity<UserResponse> getUsers(@RequestHeader String token, @RequestParam String src_field,
			@RequestParam String src_txt) {

		if (!(src_field instanceof String)) {
			src_field = "";
		}

		if (!(src_txt instanceof String)) {
			src_txt = "";
		}

		return userService.getUsers(src_field, src_txt);

	}

}
