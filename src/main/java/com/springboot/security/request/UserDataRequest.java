
package com.springboot.security.request;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataRequest {

	private String username; 
	
	private String password;

	private Set<String> role;
}
