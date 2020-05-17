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
import com.remd.spring.repository.ClinicRepository;
import com.remd.spring.repository.ItemCategoryRepository;
import com.remd.spring.repository.ItemRepository;

@Controller
public class InventoryController {
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	ItemCategoryRepository itemCategoryRepository;

	@RequestMapping(path = "/app/inventory", method = RequestMethod.GET)
	public String viewInventory(Model model, @RequestParam(name = "category", required = false) Integer categoryId) {
		model.addAttribute("itemCategories", itemCategoryRepository.findAll());
		model.addAttribute("item", new Item());
		model.addAttribute("isInventoryActive", true);
		if (categoryId == null || categoryId == -1) {
			model.addAttribute("itemList", itemRepository.findAll());
		} else {
			model.addAttribute("itemList", itemRepository.findByCategoryId(categoryId));
		}

		return "app/inventory";
	}

	@RequestMapping(path = "/app/inventory?", params = { "" })
	public String viewInventory() {
		return "redirect:app/inventory";
	}

	@RequestMapping(path = "/app/inventory/newitem", method = RequestMethod.POST)
	public String addItem(@RequestParam(name = "itemName") String itemName,
			@RequestParam(name = "itemDesc") String itemDescription, @RequestParam(name = "itemQty") int itemQuantity,
			@RequestParam(name = "itemCategory") Integer itemCategory,
			@RequestParam(name = "itemExpiryDate") @DateTimeFormat(iso = ISO.DATE) LocalDate itemExpiryDate) {
		// Do Add
		Item item = new Item(itemName, itemDescription, itemQuantity,
				itemCategoryRepository.findById(itemCategory).get(), itemExpiryDate);

		itemRepository.save(item);
		return "redirect:/app/inventory";
	}

	@RequestMapping(path = "/app/inventory/{id}", method = RequestMethod.GET)
	public String viewItemById(@PathVariable("id") Integer id, Model model) {
		Item item = itemRepository.findById(id).get();
		model.addAttribute("item", item);
		model.addAttribute("itemCategories", itemCategoryRepository.findAll());
		return "app/inventory :: editItemModalContent";
	}

	@RequestMapping(path = "/app/inventory/item/edit", method = RequestMethod.POST)
	public String editItem(@RequestParam(name = "itemId")Integer itemId,
			@RequestParam(name = "itemName") String itemName,
			@RequestParam(name = "itemDesc") String itemDescription, 
			@RequestParam(name = "itemQty") int itemQuantity,
			@RequestParam(name = "itemCategory") Integer itemCategory,
			@RequestParam(name = "itemExpiryDate") @DateTimeFormat(iso = ISO.DATE) LocalDate itemExpiryDate) {
		itemRepository.editItemById(itemName, itemDescription, itemQuantity, itemCategoryRepository.findById(itemCategory).get(), itemExpiryDate, itemId);
		return "redirect:/app/inventory";
	}
}
