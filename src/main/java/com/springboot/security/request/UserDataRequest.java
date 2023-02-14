
package com.springboot.security.request;

import java.util.Date;
import java.util.Set;

import com.springboot.security.entity.Device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataRequest {

	private String userName;

	private String email;

	private String password;

	private String address;

	private String callCharge;

	private String dob;
	
	private Device device;

	private Set<String> role;
}
