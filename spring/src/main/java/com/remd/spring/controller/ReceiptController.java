package com.remd.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.remd.spring.repository.ProcedureRepository;

@Controller
public class ReceiptController {
	@Autowired
	private ProcedureRepository procedureRepository;
	@GetMapping(path = "/app/receipt")
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
