package com.remd.spring.controller.devtools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.remd.spring.bean.User;
import com.remd.spring.bean.UserProfile;
import com.remd.spring.repository.UserRepository;

@Controller
public class NewUserController {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@RequestMapping(path = "/dev/newuser", method = RequestMethod.GET)
	public String viewPage() {
		return "dev/newuser";
	}
	
	@RequestMapping(path = "/dev/newuser/add", method = RequestMethod.POST)
	public String createUser(
			@RequestParam(name = "firstName")String firstName,
			@RequestParam(name = "lastName")String lastName,
			@RequestParam(name = "password")String password,
			@RequestParam(name = "role")String role
			) {
		User user = new User();
		user.setActive(true);
		user.setUserName(capitalizeFirstLetter(firstName).strip()+"."+capitalizeFirstLetter(lastName).strip());
		user.setPassWord(passwordEncoder.encode(password));
		user.setProfile(new UserProfile(firstName, lastName, user));
		user.setRoles("ROLE_"+role.toUpperCase());
		userRepository.save(user);
		return "redirect:/login";
	}
	
	private String capitalizeFirstLetter(String word) {
		return word.substring(0, 1).toUpperCase() + word.substring(1);
	}
}
