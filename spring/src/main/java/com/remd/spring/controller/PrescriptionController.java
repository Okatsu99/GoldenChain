package com.remd.spring.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.remd.spring.bean.MyUserDetails;

@Controller
public class PrescriptionController {
	@RequestMapping(path = "/app/prescription", method = RequestMethod.GET)
	public String viewPage(Model model) {
		model.addAttribute("profile", getUser().getUserProfile());
		return "app/prescription"; 
	}
	
	private MyUserDetails getUser() {
		return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
