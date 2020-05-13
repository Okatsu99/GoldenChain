package com.remd.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.remd.spring.bean.User;
import com.remd.spring.repository.RoleRepository;
import com.remd.spring.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@RequestMapping(path = "/user/secretary/add", method = RequestMethod.POST)
	public String addSecretary(
			@RequestParam(name = "currentUrl")String currentUrl){
		User secretary = new User();
		System.out.println(currentUrl);
		return "redirect:"+currentUrl;
	}
}
