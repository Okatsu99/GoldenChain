package com.remd.spring.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.remd.spring.model.User;
import com.remd.spring.repository.ClinicRepository;
import com.remd.spring.repository.RoleRepository;
import com.remd.spring.repository.UserRepository;
import com.remd.spring.services.EmailService;

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
	private EmailService emailService;
	
	@PostMapping(path = "/app/secretary/add")
	public String addSecretary(
			@RequestParam(name = "currentUrl")String currentUrl,
			@RequestParam(name = "sectFirstName")String firstName,
			@RequestParam(name = "sectLastName")String lastName,
			@RequestParam(name = "sectEmail")String email,
			@RequestParam(name = "sectCellNumber") String cellPhoneNumber,
			@RequestParam(name = "doctorId")Integer doctorId,
			@RequestParam(name = "clinicId")Integer clinicId
			){
		String userPass = createString();
		User secretary = new User(email, passwordEncoder.encode(userPass), Arrays.asList(roleRepository.findById(2).get()), true, true, true, true, 
				firstName, lastName, email, cellPhoneNumber,clinicRepository.findById(clinicId).get(), userRepository.findById(doctorId).get());
		userRepository.saveAndFlush(secretary);
		emailService.sendSimpleMessage("someeobscuremailaddress@gmail.com", "New Secretary", "Username: " + secretary.getEmail() + "\n Password: " + userPass);
		return "redirect:"+currentUrl;
	}
	@PostMapping(path = "/app/secretary/edit")
	public String editSecretary(
			@RequestParam(name = "currentUrl")String currentUrl,
			@RequestParam(name = "sectId")Integer id,
			@RequestParam(name = "sectFirstName")String firstName,
			@RequestParam(name = "sectLastName")String lastName,
			@RequestParam(name = "sectEmail")String email,
			@RequestParam(name = "sectCellNumber") String cellPhoneNumber,
			@RequestParam(name = "clinicId")Integer clinicId
			) {
		userRepository.editSecretaryById(firstName, lastName, email, cellPhoneNumber, clinicRepository.findById(clinicId).get(), id);
		return "redirect:"+currentUrl;
	}
	@PostMapping(path = "/app/secretary/delete")
	public String deleteSecretary(
			@RequestParam(name = "currentUrl")String currentUrl,
			@RequestParam(name = "secretaryId")List<Integer> secretaryIdList
			) {
		System.out.println(secretaryIdList);
		for (int i = 0; i < secretaryIdList.size(); i++) {
			userRepository.deleteById(secretaryIdList.get(i));
		}
		return "redirect:"+currentUrl;
	}
	@GetMapping(path = "/app/secretary/view/{id}")
	public String viewSecretary(
			Model model,
			@RequestParam(name = "currentUrl")String currentUrl,
			@PathVariable("id")Integer secretaryId
			) {
		
		model.addAttribute("secretary", userRepository.findById(secretaryId).get());
		model.addAttribute("clinicList", clinicRepository.findAll());
		model.addAttribute("currentUrl", currentUrl);
		
		return "fragments/modal/sect-edit.html";
	}
	@PostMapping(path = "/app/user/updatepass")
	public String updateUserPassword(
			@RequestParam(name = "currentUrl")String currentUrl,
			@RequestParam(name = "userId")Integer id,
			@RequestParam(name = "pass")String password,
			@RequestParam(name = "confirmPass")String confirmPass
			) {
		if(password.equals(confirmPass)) {
			userRepository.updatePassword(passwordEncoder.encode(password), id);
		}
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
