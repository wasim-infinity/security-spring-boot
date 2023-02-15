package com.springboot.security.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.security.auth.JwtUtil;
import com.springboot.security.entity.Device;
import com.springboot.security.entity.TblUserDetails;
import com.springboot.security.entity.TblUserMapping;
import com.springboot.security.repository.UserDetailsRepository;
import com.springboot.security.repository.UserMappingRepository;
import com.springboot.security.request.UserDataRequest;
import com.springboot.security.response.ResponseData;
import com.springboot.security.response.ResponseHelper;
import com.springboot.security.response.UserResponse;
import com.springboot.security.service.UserService;
import com.springboot.security.util.CommonUtils;

import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDetailsRepository userDataRepository;

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserMappingRepository userMappingRepository;
	
	private static double availableBalance = 100;

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
			newUser.setDob((LocalDate)CommonUtils.stringToDate(userDataRequest.getDob() + " 00:00:00", "dd-MM-yyyy HH:mm:ss",false));
			newUser.setRoles(Set.of("USER"));
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

	@Override
	public ResponseEntity<UserResponse> getUsers(String src_field, String src_txt) {

		UserResponse response = new UserResponse();

		// URL Decode
		if (!Objects.isNull(src_txt) || src_txt != "") {
			src_txt = String.valueOf(this.URLDecodeString_Or_ReturnAsDirected(src_txt, ""));
		}

		List<Tuple> userList = null;
		List<TblUserDetails> tblUserDetails = new ArrayList<TblUserDetails>();

		if (!Objects.isNull(src_field) && !Objects.isNull(src_txt) && src_field != "" && src_txt != "") {

			String[] src_fields = src_field.split(Pattern.quote("@-@"));
			String[] src_texts = src_txt.split(Pattern.quote("@-@"));

			if (src_fields.length == 1) {
				if (src_fields[0].contentEquals("email")) {
					userList = userDataRepository.findAllByEmail(src_texts[0]);
					response.setCount(userList.size());
				}else if(src_fields[0].contentEquals("phone")) {
					userList = userDataRepository.findAllByPhone(src_texts[0]);
					response.setCount(userList.size());
				}else if(src_fields[0].contentEquals("userName")) {
					userList = userDataRepository.findAllByUserName(src_texts[0]);
					response.setCount(userList.size());
				}else if(src_fields[0].contentEquals("address")) {
					userList = userDataRepository.findAllByAddress(src_texts[0]);
					response.setCount(userList.size());
				}
			} else if (src_fields.length == 2) {
				if (src_fields[0].contentEquals("email") && src_fields[1].contentEquals("phone")) {
					userList = userDataRepository.findAllByEmailAndPhone(src_texts[0], src_texts[1]);
					response.setCount(userList.size());
				}else if (src_fields[0].contentEquals("phone") && src_fields[1].contentEquals("email")) {
					userList = userDataRepository.findAllByPhoneEmail(src_texts[0], src_texts[1]);
					response.setCount(userList.size());
				}else if (src_fields[0].contentEquals("email") && src_fields[1].contentEquals("userName")) {
					userList = userDataRepository.findAllByEmailUserName(src_texts[0], src_texts[1]);
					response.setCount(userList.size());
				}else if (src_fields[0].contentEquals("userName") && src_fields[1].contentEquals("email")) {
					userList = userDataRepository.findAllByUserNameEmail(src_texts[0], src_texts[1]);
					response.setCount(userList.size());
				}else if (src_fields[0].contentEquals("email") && src_fields[1].contentEquals("address")) {
					userList = userDataRepository.findAllByEmailAddress(src_texts[0], src_texts[1]);
					response.setCount(userList.size());
				}else if (src_fields[0].contentEquals("address") && src_fields[1].contentEquals("email")) {
					userList = userDataRepository.findAllByAddressEmail(src_texts[0], src_texts[1]);
					response.setCount(userList.size());
				}else if (src_fields[0].contentEquals("phone") && src_fields[1].contentEquals("userName")) {
					userList = userDataRepository.findAllByPhoneUserName(src_texts[0], src_texts[1]);
					response.setCount(userList.size());
				}else if (src_fields[0].contentEquals("phone") && src_fields[1].contentEquals("address")) {
					userList = userDataRepository.findAllByPhoneAddress(src_texts[0], src_texts[1]);
					response.setCount(userList.size());
				}else if (src_fields[0].contentEquals("userName") && src_fields[1].contentEquals("phone")) {
					userList = userDataRepository.findAllByUserNamePhone(src_texts[0], src_texts[1]);
					response.setCount(userList.size());
				}else if (src_fields[0].contentEquals("address") && src_fields[1].contentEquals("phone")) {
					userList = userDataRepository.findAllByAddressPhone(src_texts[0], src_texts[1]);
					response.setCount(userList.size());
				}else if (src_fields[0].contentEquals("userName") && src_fields[1].contentEquals("Address")) {
					userList = userDataRepository.findAllByUserNameAddress(src_texts[0], src_texts[1]);
					response.setCount(userList.size());
				}else if (src_fields[0].contentEquals("address") && src_fields[1].contentEquals("UserName")) {
					userList = userDataRepository.findAllByAddressUserName(src_texts[0], src_texts[1]);
					response.setCount(userList.size());
				}
			}
		}
		
		for (Tuple tuple : userList) {
			tblUserDetails.add(this.TupleToTblUserDetails(tuple));
		}

		response.setStatusCode(HttpStatus.OK.value());
		response.setStatusMessage("Success");
		response.setUserDetailsList(tblUserDetails);
		return new ResponseEntity<UserResponse>(response, HttpStatus.OK);

	}

	public Object URLDecodeString_Or_ReturnAsDirected(String stringToBeDecoded, Object returnThisValueIfError) {

		try {
			return java.net.URLDecoder.decode(stringToBeDecoded.trim(), StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			return returnThisValueIfError;
		}

	}
	
	public TblUserDetails TupleToTblUserDetails(Tuple userList) {

		TblUserDetails tblUserDetails = new TblUserDetails();
		Device device = new Device();

		for (TupleElement<?> elements : userList.getElements()) {

			if (elements.getAlias().contentEquals("id") && !Objects.isNull(userList.get(elements.getAlias()))) {

				tblUserDetails.setId(Long.parseLong(userList.get(elements.getAlias()).toString()));
			}
			if (elements.getAlias().contentEquals("address") && !Objects.isNull(userList.get(elements.getAlias()))) {

				tblUserDetails.setAddress(userList.get(elements.getAlias()).toString());
			}
			if (elements.getAlias().contentEquals("call_charge") && !Objects.isNull(userList.get(elements.getAlias()))) {

				tblUserDetails.setCallCharge(userList.get(elements.getAlias()).toString());
			}
			if (elements.getAlias().contentEquals("dob") && !Objects.isNull(userList.get(elements.getAlias()))) {
				
				tblUserDetails.setDob((LocalDate) CommonUtils
						.stringToDate(userList.get(elements.getAlias()).toString()+ " 00:00:00", "dd-MM-yyyy HH:mm:ss", false));
			}
			if (elements.getAlias().contentEquals("email") && !Objects.isNull(userList.get(elements.getAlias()))) {

				tblUserDetails.setEmail(userList.get(elements.getAlias()).toString());
			}
			if (elements.getAlias().contentEquals("password") && !Objects.isNull(userList.get(elements.getAlias()))) {

				tblUserDetails.setPassword(userList.get(elements.getAlias()).toString());
			}
			if (elements.getAlias().contentEquals("updated_at") && !Objects.isNull(userList.get(elements.getAlias()))) {

				tblUserDetails.setUpdatedAt((Date) CommonUtils
				.stringToDate(userList.get(elements.getAlias()).toString(), "yyyy-MM-dd hh:mm:ss", true));
			}
			if (elements.getAlias().contentEquals("user_name") && !Objects.isNull(userList.get(elements.getAlias()))) {

				tblUserDetails.setUserName(userList.get(elements.getAlias()).toString());
			}
			if (elements.getAlias().contentEquals("user_name") && !Objects.isNull(userList.get(elements.getAlias()))) {

				tblUserDetails.setUserName(userList.get(elements.getAlias()).toString());
			}
			if (elements.getAlias().contentEquals("roles") && !Objects.isNull(userList.get(elements.getAlias()))) {

				tblUserDetails.setRoles(Set.of(userList.get(elements.getAlias()).toString()));
			}
			if (elements.getAlias().contentEquals("device_id") && !Objects.isNull(userList.get(elements.getAlias()))) {

				device.setId(Long.parseLong(userList.get(elements.getAlias()).toString()));
			}
			if (elements.getAlias().contentEquals("device_created_at") && !Objects.isNull(userList.get(elements.getAlias()))) {

				device.setCreatedAt((Date) CommonUtils
						.stringToDate(userList.get(elements.getAlias()).toString(), "yyyy-MM-dd hh:mm:ss", true));
			}
			if (elements.getAlias().contentEquals("imei_number") && !Objects.isNull(userList.get(elements.getAlias()))) {

				device.setImeiNumber(userList.get(elements.getAlias()).toString());
			}
			if (elements.getAlias().contentEquals("phone") && !Objects.isNull(userList.get(elements.getAlias()))) {

				device.setPhone(userList.get(elements.getAlias()).toString());
			}
			if (elements.getAlias().contentEquals("device_updated_at") && !Objects.isNull(userList.get(elements.getAlias()))) {

				device.setUpdatedAt((Date) CommonUtils
						.stringToDate(userList.get(elements.getAlias()).toString(), "yyyy-MM-dd hh:mm:ss", true));
			}
			if (elements.getAlias().contentEquals("created_at") && !Objects.isNull(userList.get(elements.getAlias()))) {

				tblUserDetails.setCreatedAt((Date) CommonUtils
						.stringToDate(userList.get(elements.getAlias()).toString(), "yyyy-MM-dd hh:mm:ss", true));
			}

		}
		tblUserDetails.setDevice(device);
		return tblUserDetails;

	}

	@Override
	public ResponseEntity<String> call(String token, long recieverId) {
		
		String callerUserName = jwtUtil.extractUsername(token);
		TblUserDetails caller = userDataRepository.findByUserName(callerUserName);
		Optional<TblUserDetails> reciever = userDataRepository.findById(recieverId);
		
		if(reciever.isEmpty()) {
			return new ResponseEntity<String>("User with id "+recieverId+" not found", HttpStatus.BAD_REQUEST);
		}
		
		TblUserMapping tblUserMapping = userMappingRepository.findByUserOneAndUserTwo(caller, reciever.get());
		
		if(!Objects.isNull(tblUserMapping)) {
			double callCharge = Double.parseDouble(reciever.get().getCallCharge());
			if(availableBalance >= callCharge) {
				availableBalance = availableBalance - callCharge;
				return new ResponseEntity<String>("Call Successfull", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Insufficient balance", HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<String>("User with id "+recieverId+" is not linked", HttpStatus.BAD_REQUEST);
		}
	
	}
	
	
	
	

}
