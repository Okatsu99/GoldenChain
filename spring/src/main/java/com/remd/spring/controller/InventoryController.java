package com.remd.spring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.remd.spring.bean.MyUserDetails;

@Controller
public class InventoryController {
	@RequestMapping(path = "/app/inventory")
	public String viewInventory(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails user = (MyUserDetails)auth.getPrincipal();
		model.addAttribute("profile", user.getUserProfile());
		return "app/inventory";
	}
}
