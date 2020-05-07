package com.remd.spring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.remd.spring.bean.MyUserDetails;

@Controller
public class ReceiptController {
	@RequestMapping(path = "/app/receipt", method = RequestMethod.GET)
	public String viewPage(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails user = (MyUserDetails)auth.getPrincipal();
		model.addAttribute("profile", user.getUserProfile());
		return "app/receipt";
	}
}
