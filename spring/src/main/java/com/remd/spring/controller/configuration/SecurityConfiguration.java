package com.remd.spring.controller.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * The WebSecurityConfig class is annotated with @EnableWebSecurity to enable Spring Security’s web security support and provide the Spring MVC integration. 
 * It also extends WebSecurityConfigurerAdapter and overrides a couple of its methods to set some specifics of the web security configuration.
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/app/**").hasAnyRole("DOCTOR","SECRETARY")
		.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/app/appointments",true)
		.and()
		.logout()
		.logoutSuccessUrl("/login")
		.and()
		.csrf().disable();
	}
	
	/*TODO: Add ecnryption*/
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
