package com.springboot.security.response;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.springboot.security.entity.TblUserDetails;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class UserResponse {

	private String statusMessage;

	private int statusCode;

	private List<TblUserDetails> userDetailsList;
	
	private long count;

}
