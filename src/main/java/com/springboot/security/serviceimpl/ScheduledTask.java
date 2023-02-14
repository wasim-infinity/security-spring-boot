package com.springboot.security.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.springboot.security.entity.TblUserDetails;
import com.springboot.security.repository.UserDetailsRepository;
import com.springboot.security.request.EmailDetails;
import com.springboot.security.service.EmailService;

@Service
public class ScheduledTask {

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Scheduled(cron = "0 0 * * *")
	public void SendEmail() {
		
		EmailDetails details = new EmailDetails();
		details.setSubject("Birthday Greeting");
		List<TblUserDetails> userDetailsList = userDetailsRepository.findAll();
		for (TblUserDetails user : userDetailsList) {
			if(user.getDob() == null) {
				continue;
			}
			LocalDate dob = user.getDob();
			if (dob.equals(LocalDate.now())) {
				System.out.println("mail sent to "+user.getUserName());
				details.setMsgBody("Happy Birthday "+user.getUserName()+" !!!");
				details.setRecipient(user.getEmail());
				emailService.sendMail(details);
			}
		}

	}

}
