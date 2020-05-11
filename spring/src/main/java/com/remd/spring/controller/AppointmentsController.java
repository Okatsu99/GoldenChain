package com.remd.spring.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.remd.spring.bean.MyUserDetails;

@Controller
public class AppointmentsController {
	@GetMapping("/app/appointments")
	public String viewAppointments(Model model) {
		model.addAttribute("profile", getUser().getUserProfile());
		return "app/appointments";
	}
	
	private MyUserDetails getUser() {
		return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
