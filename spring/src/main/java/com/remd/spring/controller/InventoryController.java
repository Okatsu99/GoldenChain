package com.remd.spring.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.remd.spring.bean.Item;
import com.remd.spring.bean.MyUserDetails;
import com.remd.spring.repository.ItemCategoryRepository;
import com.remd.spring.repository.ItemRepository;

@Controller
public class InventoryController {
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	ItemCategoryRepository itemCategoryRepository;
	@RequestMapping(path = "/app/inventory", method = RequestMethod.GET)
	public String viewInventory(Model model) {
		model.addAttribute("profile", getUser().getUserProfile());
		model.addAttribute("itemCategories", itemCategoryRepository.findAll());
		model.addAttribute("item",new Item());
		model.addAttribute("itemList", itemRepository.findAll());
		return "app/inventory";
	}
	@RequestMapping(path = "/app/newitem", method = RequestMethod.POST)
	public String addItem(
			@RequestParam(name = "itemName")String itemName,
			@RequestParam(name = "itemDesc")String itemDescription,
			@RequestParam(name = "itemQty")int itemQuantity,
			@RequestParam(name = "itemCategory")Integer itemCategory,
			@RequestParam(name = "itemExpiryDate")
				@DateTimeFormat(iso = ISO.DATE)LocalDate itemExpiryDate,
			@RequestParam(name = "itemDoctorNotes")String itemNotes) {
		//Do Add
		Item item = new Item(itemName, itemDescription, itemQuantity, 
				itemCategoryRepository.findById(itemCategory).get(), itemExpiryDate, itemNotes);
		
		itemRepository.save(item);
		return "redirect:/app/inventory";
	}
	@RequestMapping(path = "/app/inventory/{id}", method = RequestMethod.GET)
	public String viewRecord(@PathVariable("id") Integer id, Model model) {
		Item item = itemRepository.findById(id).get();
		model.addAttribute("item", item);
		model.addAttribute("itemCategories", itemCategoryRepository.findAll());
		return "app/inventory :: editItemModalContent";
	}
	private MyUserDetails getUser() {
		return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
