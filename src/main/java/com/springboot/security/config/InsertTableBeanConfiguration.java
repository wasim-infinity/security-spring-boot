//package com.springboot.security.config;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import com.springboot.security.entity.TblUserDetails;
//import com.springboot.security.repository.UserDetailsRepository;
//
//@Configuration
//@Component
//public class InsertTableBeanConfiguration {
//
//	@Autowired
//	private UserDetailsRepository userDtlsRepository;
//
//	@Bean
//	public void saveAdmin() {
//		userDtlsRepository.deleteAll();
//		userDtlsRepository.truncateTable();
//		userDtlsRepository.save(new TblUserDetails(1, "wasim", "12345", "USER", new Date(), new Date()));
//		userDtlsRepository.save(new TblUserDetails(2, "akram", "54321", "ADMIN", new Date(), new Date()));
//		userDtlsRepository.save(new TblUserDetails(3, "ram", "12345", "", new Date(), new Date()));
//	}
//
//}
