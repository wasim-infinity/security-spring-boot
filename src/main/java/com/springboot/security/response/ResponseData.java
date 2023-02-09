package com.springboot.security.response;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ResponseData {

	private String statusMessage;

	private int statusCode;
	
	private String userName;

	private String authToken;

}
