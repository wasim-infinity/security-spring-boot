package com.springboot.security.serviceimpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.security.entity.TblUserDetails;
import com.springboot.security.entity.TblUserMapping;
import com.springboot.security.repository.UserDetailsRepository;
import com.springboot.security.repository.UserMappingRepository;
import com.springboot.security.request.MapUserRequest;
import com.springboot.security.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserMappingRepository userMappingRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public ResponseEntity<String> mapUsers(MapUserRequest mapUserResquest) {

		Optional<TblUserDetails> user1 = userDetailsRepository.findById(mapUserResquest.getId1());
		Optional<TblUserDetails> user2 = userDetailsRepository.findById(mapUserResquest.getId2());

		if (user1.isEmpty()) {
			return new ResponseEntity<String>("User with id "+mapUserResquest.getId1()+" not found", HttpStatus.BAD_REQUEST);
		}
		if (user2.isEmpty()) {
			return new ResponseEntity<String>("User with id "+mapUserResquest.getId2()+" not found", HttpStatus.BAD_REQUEST);
		}

		TblUserMapping tblUserMapping = new TblUserMapping();
		tblUserMapping.setUserOne(user1.get());
		tblUserMapping.setUserTwo(user2.get());
		tblUserMapping.setCreatedAt(LocalDateTime.now());
		userMappingRepository.save(tblUserMapping);

		return new ResponseEntity<String>("users successfully mapped", HttpStatus.OK);

	}

}
