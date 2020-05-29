package com.remd.spring.controller.services.email;

import java.util.Collections;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

public class SpringMailConfig implements ApplicationContextAware, EnvironmentAware {
    public static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";
	private ApplicationContext applicationContext;
	private Environment environment;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Bean
	public JavaMailSender mailService() {
		final JavaMailSenderImpl mailService = new JavaMailSenderImpl();
		mailService.setHost(environment.getProperty("spring.mail.host"));
		mailService.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
		mailService.setUsername(environment.getProperty("spring.mail.username"));
		mailService.setPassword(environment.getProperty("spring.mail.password"));
		Properties mailServiceProp = new Properties();
		mailServiceProp.setProperty("spring.mail.properties.mail.smtp.auth",
				environment.getProperty("spring.mail.properties.mail.smtp.auth"));
		mailServiceProp.setProperty("spring.mail.properties.mail.smtp.starttls.enable",
				environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
		return mailService;
	}

	/*
	 * 
	 * SOURCE = https://github.com/thymeleaf/thymeleafexamples-springmail/blob/3.0-master/src/main/java/thymeleafexamples/springmail/business/SpringMailConfig.java
	 * 
	 * Message externalization/internationalization for emails.
	 *
	 * NOTE we are avoiding the use of the name 'messageSource' for this bean
	 * because that would make the MessageSource defined in SpringWebConfig (and
	 * made available for the web-side template engine) delegate to this one, and
	 * thus effectively merge email messages into web messages and make both types
	 * available at the web side, which could bring undesired collisions.
	 *
	 * NOTE also that given we want this specific message source to be used by our
	 * SpringTemplateEngines for emails (and not by the web one), we will set it
	 * explicitly into each of the TemplateEngine objects with
	 * 'setTemplateEngineMessageSource(...)'
	 */
	@Bean
	public ResourceBundleMessageSource emailMessageSource() {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("mail/MailMessages");
		return messageSource;
	}
	
	 @Bean
	    public TemplateEngine emailTemplateEngine() {
	        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	        // Resolver for TEXT emails
	        templateEngine.addTemplateResolver(textTemplateResolver());
	        // Resolver for HTML emails (except the editable one)
	        templateEngine.addTemplateResolver(htmlTemplateResolver());
	        // Resolver for HTML editable emails (which will be treated as a String)
	        templateEngine.addTemplateResolver(stringTemplateResolver());
	        // Message source, internationalization specific to emails
	        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
	        return templateEngine;
	    }

	    private ITemplateResolver textTemplateResolver() {
	        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	        templateResolver.setOrder(Integer.valueOf(1));
	        templateResolver.setResolvablePatterns(Collections.singleton("text/*"));
	        templateResolver.setPrefix("/mail/");
	        templateResolver.setSuffix(".txt");
	        templateResolver.setTemplateMode(TemplateMode.TEXT);
	        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
	        templateResolver.setCacheable(false);
	        return templateResolver;
	    }

	    private ITemplateResolver htmlTemplateResolver() {
	        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	        templateResolver.setOrder(Integer.valueOf(2));
	        templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
	        templateResolver.setPrefix("/mail/");
	        templateResolver.setSuffix(".html");
	        templateResolver.setTemplateMode(TemplateMode.HTML);
	        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
	        templateResolver.setCacheable(false);
	        return templateResolver;
	    }

	    private ITemplateResolver stringTemplateResolver() {
	        final StringTemplateResolver templateResolver = new StringTemplateResolver();
	        templateResolver.setOrder(Integer.valueOf(3));
	        // No resolvable pattern, will simply process as a String template everything not previously matched
	        templateResolver.setTemplateMode("HTML5");
	        templateResolver.setCacheable(false);
	        return templateResolver;
	    }
}
