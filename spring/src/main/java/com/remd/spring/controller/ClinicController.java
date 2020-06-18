package com.remd.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.remd.spring.model.Clinic;
import com.remd.spring.model.MyUserDetails;
import com.remd.spring.repository.ClinicRepository;

@Controller
public class ClinicController {
	private static final Logger log = LoggerFactory.getLogger(ClinicController.class);
	@Autowired
	private ClinicRepository clinicRepository;

	@RequestMapping(path = "/app/clinic/new", method = RequestMethod.POST)
	public String addClinic(@RequestParam(name = "clinicName") String name,
			@RequestParam(name = "clinicAddress") String address,
			@RequestParam(name = "currentUrl") String redirect) {
		MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		clinicRepository.save(new Clinic(name, address));
		log.info("Doctor " + currentUser.getUser().getFullNameFormatted() + " has a added a new clinic named " + name);
		return "redirect:" + redirect;
	}

	@PostMapping("/app/clinic/delete")
	public String deleteClinic(@RequestParam(name = "currentUrl") String currentUrl,
			@RequestParam(name = "clinicId") List<Integer> clinicIdList) {
		MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		for (int i = 0; i < clinicIdList.size(); i++) {
			log.info("Doctor " + currentUser.getUser().getFullNameFormatted() + "has deleted their clinic: " + clinicRepository.getOne(clinicIdList.get(i)));
			clinicRepository.deleteById(clinicIdList.get(i));
		}
		return "redirect:" + currentUrl;
	}
}
