package com.remd.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.remd.spring.bean.MyUserDetails;
import com.remd.spring.repository.ClinicRepository;

@Controller
public class AppointmentsController {
	@GetMapping("/app/appointments")
	public String viewAppointments(Model model) {
		model.addAttribute("isAppointmentActive", true);
		return "app/appointments";
	}
}
