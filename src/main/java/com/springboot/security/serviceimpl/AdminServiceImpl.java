package com.springboot.security.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.security.entity.TblUserMapping;
import com.springboot.security.repository.UserMappingRepository;
import com.springboot.security.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private UserMappingRepository userMappingRepository;

	public ResponseEntity<String> mapUsers() {
		
		TblUserMapping tblUserMapping = new TblUserMapping();
		tblUserMapping.setUserOne(null);
		tblUserMapping.setUserTwo(null);
		tblUserMapping.setCreatedAt(LocalDateTime.now());
		userMappingRepository.save(tblUserMapping);

		return new ResponseEntity<String>("users successfully mapped", HttpStatus.OK);

	}

}
