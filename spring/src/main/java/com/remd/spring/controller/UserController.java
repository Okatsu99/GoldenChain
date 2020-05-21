package com.remd.spring.controller;

import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.remd.spring.controller.services.MyEmailService;
import com.remd.spring.model.User;
import com.remd.spring.repository.ClinicRepository;
import com.remd.spring.repository.RoleRepository;
import com.remd.spring.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ClinicRepository clinicRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MyEmailService emailService;
	@PostMapping(path = "/app/secretary/add")
	public String addSecretary(
			@RequestParam(name = "currentUrl")String currentUrl,
			@RequestParam(name = "sectFirstName")String firstName,
			@RequestParam(name = "sectLastName")String lastName,
			@RequestParam(name = "sectEmail")String email,
			@RequestParam(name = "doctorId")Integer doctorId,
			@RequestParam(name = "clinicId")Integer clinicId
			){
		String emailBody;
		String userPass = createString();
		User secretary = new User(email, passwordEncoder.encode(userPass), Arrays.asList(roleRepository.findById(2).get()), true, true, true, true, 
				firstName, lastName, email, clinicRepository.findById(clinicId).get(), userRepository.findById(doctorId).get());
		userRepository.saveAndFlush(secretary);
		emailBody = "Username: " + email + "\nPassword: " + userPass;
		emailService.sendSimpleMessage("someeobscuremailaddress@gmail.com", "New Secretary", emailBody);
		return "redirect:"+currentUrl;
	}
	public String updateUserPassword(
			@RequestParam(name = "currentUrl")String currentUrl) {
		return "redirect:"+currentUrl;
	}
	private static String createString() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		System.out.println(generatedString);
		return generatedString;
	}
	
}
