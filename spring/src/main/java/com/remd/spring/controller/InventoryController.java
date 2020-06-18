package com.remd.spring.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.remd.spring.model.Item;
import com.remd.spring.model.MyUserDetails;
import com.remd.spring.repository.ClinicRepository;
import com.remd.spring.repository.ItemCategoryRepository;
import com.remd.spring.repository.ItemRepository;
import com.remd.spring.repository.RoleRepository;

@Controller
public class InventoryController {
	private static final Logger log = LoggerFactory.getLogger(InventoryController.class);
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ItemCategoryRepository itemCategoryRepository;
	@Autowired
	private ClinicRepository clinicRepository;
	@Autowired
	private RoleRepository roleRepository;

	@GetMapping(path = "/app/inventory")
	public String viewInventory(Model model,
			HttpServletRequest request,
			@RequestParam(name = "category", required = false) Integer categoryId,
			@RequestParam(name = "order", defaultValue = "-1") Integer order) {
		List<Item> itemList = null;
		MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		model.addAttribute("itemCategories", itemCategoryRepository.findAll());
		model.addAttribute("item", new Item());
		model.addAttribute("isInventoryActive", true);
		// User is Doctor else via Items in Clinic of Secretary
		if (request.isUserInRole(roleRepository.findById(1).get().getName())) {
			itemList = itemRepository.findByIsActiveTrue();
		} else {
			itemList = itemRepository.findAllByItemLocationAndIsActiveTrue(currentUser.getUser().getClinic());
		}
		Collections.reverse(itemList);
		model.addAttribute("itemList", itemList);
		return "app/inventory";
	}

	@GetMapping(path = "/app/inventory", params = { "filter" })
	public String viewInventoryFilteredBy(@RequestParam(name = "filter") Integer filter,
			HttpServletRequest request,
			Model model) {
		MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		System.out.println("Filtering");
		// Check if User is in Role #1 ROLE_DOCTOR
		if (request.isUserInRole(roleRepository.findById(1).get().getName())) {
			// Show All of doctors Inventory things
			if (filter < 1) {
				model.addAttribute("itemList", itemRepository.findByIsActiveTrue());
			} else {
				// Show All by Items
				model.addAttribute("itemList", itemRepository.findByCategoryIdAndIsActiveTrue(filter));
			}
		} else { // User is a secretary
			// Show All Items in Secretary's Clinic
			if (filter < 1) {
				model.addAttribute("itemList", itemRepository.findAllByItemLocationAndIsActiveTrue(
						clinicRepository.findById(currentUser.getUser().getClinic().getId()).get()));
			} else {
				model.addAttribute("itemList",
						itemRepository.findAllByItemLocationAndCategoryAndIsActiveTrue(
								clinicRepository.findById(currentUser.getUser().getClinic().getId()).get(),
								itemCategoryRepository.findById(filter).get()));
			}
		}
		return "app/inventory :: inventoryTableBody";
	}

	@PostMapping(path = "/app/inventory/newitem")
	public String addItem(@RequestParam(name = "itemName") String itemName,
			@RequestParam(name = "itemDesc") String itemDescription,
			@RequestParam(name = "itemQty") int itemQuantity,
			@RequestParam(name = "itemCategory") Integer itemCategory,
			@RequestParam(name = "itemExpiryDate") @DateTimeFormat(iso = ISO.DATE) LocalDate itemExpiryDate,
			@RequestParam(name = "itemLocation") Integer itemLocation) {
		MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		// Do Add
		Item item = new Item(itemName, itemDescription, itemQuantity,
				itemCategoryRepository.findById(itemCategory).get(), itemExpiryDate,
				clinicRepository.findById(itemLocation).get());

		itemRepository.save(item);
		log.info("User " + currentUser.getUser().getFullNameFormatted() + " has added a new item named "
				+ item.getName() + " to clinic " + item.getItemLocation().getName());
		return "redirect:/app/inventory";
	}

	@GetMapping(path = "/app/inventory/item/view/{id}")
	public String viewItemById(@PathVariable(name = "id") Integer id, Model model) {
		Item item = itemRepository.findById(id).get();
		model.addAttribute("item", item);
		model.addAttribute("itemCategories", itemCategoryRepository.findAll());
		return "app/inventory :: editItemModalContent";
	}

	@RequestMapping(path = "/app/inventory/item/edit", method = RequestMethod.POST)
	public String editItem(@RequestParam(name = "itemId") Integer itemId,
			@RequestParam(name = "itemName") String itemName,
			@RequestParam(name = "itemDesc") String itemDescription,
			@RequestParam(name = "itemQty") int itemQuantity,
			@RequestParam(name = "itemCategory") Integer itemCategory,
			@RequestParam(name = "itemExpiryDate") @DateTimeFormat(iso = ISO.DATE) LocalDate itemExpiryDate) {
		MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		itemRepository.editItemById(itemName, itemDescription, itemQuantity,
				itemCategoryRepository.findById(itemCategory).get(), itemExpiryDate, itemId);
		log.info("User " + currentUser.getUser().getFullNameFormatted() + "has edited item[" + itemId + "] of clinic "
				+ itemRepository.getOne(itemId).getItemLocation().getLocation());
		return "redirect:/app/inventory";
	}

	@PostMapping(path = "/app/inventory/delete")
	public String deleteItem(@RequestParam(name = "itemId") List<Integer> itemIdList) {
		MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		for (Integer itemId : itemIdList) {
			log.info("User " + currentUser.getUser().getFullNameFormatted() + " has deleted item[" + itemId + "]:"
					+ itemRepository.getOne(itemId).getName() + " of clinic "
					+ itemRepository.getOne(itemId).getItemLocation().getName());
			itemRepository.logicalDeleteItem(false, itemId);
		}
		return "redirect:/app/inventory";
	}
}
