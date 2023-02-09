package com.springboot.security.response;

public class ResponseHelper {

	public static ResponseData responseSender(String statusMessage, int statusCode) {
		ResponseData response = new ResponseData();
		response.setStatusCode(statusCode);
		response.setStatusMessage(statusMessage);
		return response;
	}


	public static ResponseData responseSender(String statusMessage, int statusCode, String token, String userName) {
		ResponseData response = new ResponseData();
		response.setStatusCode(statusCode);
		response.setStatusMessage(statusMessage);
		response.setAuthToken(token);
		response.setUserName(userName);
		return response;
	}


}
