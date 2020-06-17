package com.remd.spring.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.remd.spring.model.Appointment;
import com.remd.spring.repository.AppointmentRepository;
import com.sun.mail.handlers.message_rfc822;

@Service
public class EmailService {
	private static final String PNG_MIME = "image/png";

	@Autowired
	private JavaMailSender emailSender;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private SpringTemplateEngine thymeleafTemplateEngine;
	@Value("classpath:/static/images/email/logo.png")
	private Resource logoImg;
	@Value("classpath:/static/images/email/rx-logo.png")
	private Resource rxLogo;

	public void sendPrescriptionEmailTemplate(String to, String subject, Map<String, Object> templateModel)
			throws MessagingException {
		Context thymeleafContext = new Context();
		thymeleafContext.setVariables(templateModel);

		String htmlBody = thymeleafTemplateEngine.process("prescription", thymeleafContext);
		sendPrescriptionEmail(to, subject, htmlBody);
	}

	public void sendReceiptEmailTemplate(String to, String subject, Map<String, Object> templateModel)
			throws MessagingException {
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

	public void sendSimpleMessage(String to, String subject, String text) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);

			emailSender.send(message);
		} catch (MailException exception) {
			exception.printStackTrace();
		}
	}
	// */59 * * * * * = every 59th Second, use this to test
	// * 0 12 * * * = Every 12PM
	@Scheduled(cron = "* 0 12 * * *")
	public void sendEmailNotificationDayBeforeAppointment() {
		System.out.println("Performing Cron for Appointment Emails");
		LocalDate today = LocalDate.now();
		List<Appointment> appointmentsTomorrow = appointmentRepository.getAllAppointmentsForTomorrow(today.plusDays(1).toString());
		System.out.println( "Appointment list size:  "+ appointmentsTomorrow.size());
		if(appointmentsTomorrow.size() > 0) {
			for (Appointment appointment : appointmentsTomorrow) {
				String msgBody = "Doctor Appointment at " + appointment.getRecord().getPatientClinic().getLocation() + "\n\nTime: " + appointment.getAppointmentTime();
				sendSimpleMessage("someeobscuremailaddress@gmail.com", "Doctor Appointment on " + appointment.getTimeSlot().toLocalDate().toString(), msgBody);
			}
		};
	}
}
