package com.remd.spring.controller.devtools;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.remd.spring.bean.User;

@Controller
public class NewUserController {
	
	@RequestMapping(path = "/test/newuser", method = RequestMethod.GET)
	public String viewPage() {
		return "/test/newuser";
	}
	@RequestMapping(path = "/test/newuser/new", method = RequestMethod.POST)
	public String createUser(
			@RequestParam(name = "user")User user
			) {
		return "redirect:login";
	}
}
