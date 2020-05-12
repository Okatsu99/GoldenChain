package com.remd.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@RequestMapping(path = "/app/user", method = RequestMethod.GET)
	public String addSecretary(
			@RequestParam(name = "currentUrl")String currentUrl){
		System.out.println(currentUrl);
		return "redirect:"+currentUrl;
	}
}
