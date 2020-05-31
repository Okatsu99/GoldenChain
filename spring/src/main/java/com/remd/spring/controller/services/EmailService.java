package com.remd.spring.controller.services;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public class EmailService {
	private static final String BACKGROUND_IMAGE = "mail/editablehtml/images/background.png";
    private static final String PNG_MIME = "image/png";
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	@Autowired
	private TemplateEngine stringTemplateEngine;
	
	/*
     * Send HTML mail with inline image
     */
    public void sendEditableMail(
            String recipientName,String recipientEmail, Map<String,Object> templateObjects)
            throws MessagingException {

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message
                = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
        message.setSubject("Example editable HTML email");
        message.setFrom("thymeleaf@example.com");
        message.setTo(recipientEmail);

        // Prepare the evaluation context
        Context ctx = new Context();

        // Create the HTML body using Thymeleaf
        String output = stringTemplateEngine.process("email/receipt.html", ctx);
        message.setText(output, true /* isHtml */);

        // Add the inline images, referenced from the HTML code as "cid:image-name"
        message.addInline("background", new ClassPathResource(BACKGROUND_IMAGE), PNG_MIME);

        // Send mail
        this.mailSender.send(mimeMessage);
    }
}
