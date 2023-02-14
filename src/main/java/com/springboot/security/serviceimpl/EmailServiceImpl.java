package com.springboot.security.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.springboot.security.request.EmailDetails;
import com.springboot.security.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	public ResponseEntity<String> sendMail(EmailDetails details) {

		try {

			SimpleMailMessage mailMessage = new SimpleMailMessage();

			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());

			javaMailSender.send(mailMessage);
			return new ResponseEntity<String>("Mail Sent Successfully...", HttpStatus.OK);
		}

		catch (Exception e) {
			return new ResponseEntity<String>("Error while Sending Mail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
