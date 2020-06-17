package com.remd.spring.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.remd.spring.model.MyUserDetails;
import com.remd.spring.model.Procedure;
import com.remd.spring.model.User;
import com.remd.spring.repository.ProcedureRepository;
import com.remd.spring.services.EmailService;

@Controller
public class ReceiptController {
	@Autowired
	private ProcedureRepository procedureRepository;
	@Autowired
	EmailService emailService;

	@GetMapping(path = "/app/receipt")
	public String viewPage(Model model) {
		model.addAttribute("isReceiptActive", true);
		model.addAttribute("procedures", procedureRepository.findAll());
		return "app/receipt";
	}

	@GetMapping(path = "/app/receipt/addprocedure")
	public String getProcedureFragment(Model model) {
		model.addAttribute("procedures", procedureRepository.findAll());
		return "app/receipt::procedureInput";
	}

	@PostMapping(path = "/app/receipt/email")
	public String sendReceiptEmail(
			@RequestParam(name = "prodcedureItem") List<Integer> procedureId) throws MessagingException {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Procedure> procedures = new ArrayList<Procedure>();
		Procedure procedureTemp = null;
		BigDecimal totalPrice = new BigDecimal(0);
		for (int i = 0; i < procedureId.size(); i++) {
			procedureTemp = procedureRepository.findById(procedureId.get(i)).get();
			procedureTemp.setPrice(procedureTemp.getPrice().setScale(2, RoundingMode.HALF_EVEN));
			totalPrice = totalPrice.add(procedureTemp.getPrice()); 
			procedures.add(procedureTemp);
		}
		totalPrice = totalPrice.setScale(2, RoundingMode.HALF_EVEN);
		
		User currentUserDetails = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
		params.put("doctor", currentUserDetails);
		params.put("procedures", procedures);
		params.put("total", totalPrice);
		emailService.sendReceiptEmailTemplate("someeobscuremailaddress@gmail.com", "test", params);
		return "redirect:/app/receipt";
	}

}
