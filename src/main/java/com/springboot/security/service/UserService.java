package com.springboot.security.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.security.request.UserDataRequest;
import com.springboot.security.response.ResponseData;
import com.springboot.security.response.UserResponse;

@Service
public interface UserService {

	ResponseEntity<ResponseData> createAccount(UserDataRequest userDataRequest);

	ResponseEntity<UserResponse> getUsers(String src_field, String src_txt);

}
