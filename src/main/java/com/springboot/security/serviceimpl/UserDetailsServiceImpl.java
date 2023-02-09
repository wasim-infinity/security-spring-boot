package com.springboot.security.serviceimpl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.security.entity.TblUserDetails;
import com.springboot.security.repository.UserDetailsRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userDataRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TblUserDetails userDetails = userDataRepository.findByUserName(username);
		return new User(userDetails.getUserName(), userDetails.getPassword(), userDetails.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
	}
}
