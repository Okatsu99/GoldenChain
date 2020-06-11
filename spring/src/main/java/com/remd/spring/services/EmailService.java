package com.remd.spring.services;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
@Service
public class EmailService {
	private static final String PNG_MIME = "image/png";
	
	@Autowired
	private JavaMailSender emailSender;
	@Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;
	@Value("classpath:/static/images/email/logo.png")
	private Resource logoImg;
	@Value("classpath:/static/images/email/rx-logo.png")
	private Resource rxLogo;
	
	public void sendPrescriptionEmailTemplate(String to, String subject, Map<String, Object> templateModel) throws MessagingException {
		Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        
        String htmlBody = thymeleafTemplateEngine.process("prescription", thymeleafContext);
        sendPrescriptionEmail(to, subject, htmlBody);
	}
	public void sendReceiptEmailTemplate(String to, String subject, Map<String, Object> templateModel) throws MessagingException {
		Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        
        String htmlBody = thymeleafTemplateEngine.process("receipt", thymeleafContext);
        sendReceiptEmail(to, subject, htmlBody);
	}
	private void sendPrescriptionEmail(String to, String subject, String htmlBody) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(htmlBody, true);
		helper.addInline("logo", logoImg, PNG_MIME);
		helper.addInline("rxLogo", rxLogo, PNG_MIME);
		emailSender.send(message);
	}
	
	private void sendReceiptEmail(String to, String subject, String htmlBody) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(htmlBody, true);
		helper.addInline("logo", logoImg, PNG_MIME);
		emailSender.send(message);
	}
}
