package com.remd.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppointmentsController {
	@GetMapping("/app/appointments")
	public String viewAppointments(Model model) {
		model.addAttribute("isAppointmentActive", true);
		return "app/appointments";
	}
}
