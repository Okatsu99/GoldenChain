package com.remd.spring.controller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class MyEmailService {
	@Autowired
	private JavaMailSender emailService;
	
	public void sendSimpleMessage(String receiverAddress, String subject, String messageBody) {
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(receiverAddress);
			msg.setSubject(subject);
			msg.setText(messageBody);
			emailService.send(msg);
		} catch (MailException me) {
			me.printStackTrace();
		}
	}
}
