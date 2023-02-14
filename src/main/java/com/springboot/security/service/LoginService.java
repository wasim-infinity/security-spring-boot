package com.springboot.security.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.security.request.LoginRequest;
import com.springboot.security.response.ResponseData;

@Service
public interface LoginService {

	ResponseEntity<ResponseData> generateToken(LoginRequest loginRequest);

}
