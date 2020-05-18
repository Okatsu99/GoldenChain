package com.remd.spring.controller.devtools;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.remd.spring.bean.User;
import com.remd.spring.repository.RoleRepository;
import com.remd.spring.repository.UserRepository;

@Controller
public class NewUserController {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@RequestMapping(path = "/dev/newuser", method = RequestMethod.GET)
	public String viewPage() {
		return "dev/newuser";
	}
	
	@RequestMapping(path = "/dev/newuser/add", method = RequestMethod.POST)
	public String createUser(
			@RequestParam(name = "firstName")String firstName,
			@RequestParam(name = "lastName")String lastName,
			@RequestParam(name = "password")String password,
			@RequestParam(name = "email")String email,
			@RequestParam(name = "role")Integer roleId
			) {
		User user = new User();
		user.setAccountEnabled(true);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setFirstName(capitalizeFirstLetter(firstName));
		user.setLastName(capitalizeFirstLetter(lastName));
		user.setUserName(email);
		user.setPassWord(passwordEncoder.encode(password));
		user.setEmail(email);
		user.setDoctor(null);
		user.setSecretaries(null);
		user.setRoles(Arrays.asList(roleRepository.findById(roleId).get()));
		userRepository.save(user);
		return "redirect:/login";
	}
	
	private String capitalizeFirstLetter(String word) {
		return word.substring(0, 1).toUpperCase() + word.substring(1);
	}
}
