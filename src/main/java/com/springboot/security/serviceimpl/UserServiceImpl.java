package com.springboot.security.serviceimpl;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.security.entity.Device;
import com.springboot.security.entity.TblUserDetails;
import com.springboot.security.repository.UserDetailsRepository;
import com.springboot.security.request.UserDataRequest;
import com.springboot.security.response.ResponseData;
import com.springboot.security.response.ResponseHelper;
import com.springboot.security.service.UserService;
import com.springboot.security.util.CommonUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDetailsRepository userDataRepository;

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	@Override
	public ResponseEntity<ResponseData> createAccount(UserDataRequest userDataRequest) {

		TblUserDetails userData = userDataRepository.findByUserName(userDataRequest.getUserName());
		ResponseData response = null;
		if (Objects.isNull(userData)) {

			Device device = new Device();
			device.setPhone(userDataRequest.getDevice().getPhone());
			device.setImeiNumber(userDataRequest.getDevice().getImeiNumber());
			device.setCreatedAt(new Date());

			TblUserDetails newUser = new TblUserDetails();
			newUser.setUserName(userDataRequest.getUserName());
			newUser.setEmail(userDataRequest.getEmail());
			newUser.setPassword(pwdEncoder.encode(userDataRequest.getPassword()));
			newUser.setAddress(userDataRequest.getAddress());
			newUser.setCallCharge(userDataRequest.getCallCharge());
			newUser.setDob(CommonUtils.stringToDate(userDataRequest.getDob() + " 00:00:00", "dd-MM-yyyy HH:mm:ss"));
			newUser.setRoles(userDataRequest.getRole());
			newUser.setCreatedAt(new Date());
			newUser.setDevice(device);

			userData = userDataRepository.save(newUser);

		} else {

			response = ResponseHelper.responseSender(
					"user with username : " + userDataRequest.getUserName() + " already exists !!!.",
					HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<ResponseData>(response, HttpStatus.BAD_REQUEST);

		}

		if (userData.getId() > 0) {

			response = ResponseHelper.responseSender("Successfully account created !!!.", HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseData>(response, HttpStatus.CREATED);

		} else {

			response = ResponseHelper.responseSender("Unable to create account !!!. Please try again.",
					HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<ResponseData>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

}
