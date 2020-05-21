package com.remd.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.remd.spring.model.Clinic;
import com.remd.spring.repository.ClinicRepository;

@Controller
public class ClinicController {
	@Autowired
	private ClinicRepository clinicRepository;
	@RequestMapping(path = "/clinic/new", method = RequestMethod.POST)
	public String addClinic(
			@RequestParam(name = "clinicName")String name,
			@RequestParam(name = "clinicAddress")String address,
			@RequestParam(name = "currentUrl")String redirect
			) {
		clinicRepository.save(new Clinic(name,address));
		return "redirect:"+redirect;
	}
}
