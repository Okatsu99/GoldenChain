package com.remd.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.remd.spring.bean.MyUserDetails;
import com.remd.spring.repository.ClinicRepository;
import com.remd.spring.repository.ProcedureRepository;

@Controller
public class ReceiptController {
	@Autowired
	private ProcedureRepository procedureRepository;
	@RequestMapping(path = "/app/receipt", method = RequestMethod.GET)
	public String viewPage(Model model){
		model.addAttribute("isReceiptActive", true);
		model.addAttribute("procedures",procedureRepository.findAll());
		return "app/receipt";
	}
	
	@GetMapping(path = "/app/receipt/addprocedure")
	public String getMedicineFragment(Model model) {
		model.addAttribute("procedures",procedureRepository.findAll());
		return "app/receipt::procedureInput";
	}
}
