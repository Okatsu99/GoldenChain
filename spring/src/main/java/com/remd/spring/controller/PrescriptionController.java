package com.remd.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PrescriptionController {
	@RequestMapping(path = "/app/prescription", method = RequestMethod.GET)
	public String viewPage(Model model) {
		model.addAttribute("isPrescriptionActive", true);
		return "app/prescription"; 
	}
}