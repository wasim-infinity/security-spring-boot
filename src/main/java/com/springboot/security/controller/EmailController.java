package com.springboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.request.EmailDetails;
import com.springboot.security.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/sendMail")
	public ResponseEntity<String> sendMail(@RequestBody EmailDetails details) {
		return emailService.sendMail(details);
	}

}
