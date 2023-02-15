package com.springboot.security.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

	public ResponseEntity<String> mapUsers();

}
