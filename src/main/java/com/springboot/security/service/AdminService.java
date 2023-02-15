package com.springboot.security.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.security.request.MapUserRequest;

@Service
public interface AdminService {

	public ResponseEntity<String> mapUsers(MapUserRequest mapUserResquest);

}
