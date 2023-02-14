//package com.springboot.security.config;
//
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.Set;
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
//		userDtlsRepository.save(new TblUserDetails(1, "admin", "$2a$10$a5W1ASXLrV5eG6Rpp.830eUK8iF0JpHcGPUo24q8E/tuRfWgL9LpK", 
//				Set.of("ADMIN"), LocalDate.now(), LocalDate.now()));
//	}
//
//}
