package com.remd.spring.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "item_list")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "quantity")
	private Integer quantity;
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private ItemCategory category;
	@Column(name = "expiration_date")
	private LocalDate expiration;
	@ManyToOne
	@JoinColumn(name = "clinic_id", nullable = false)
	private Clinic itemLocation;
	
	public Item() {
		this.name = "";
		this.description = "";
		this.quantity = 0;
		this.expiration = null;
		this.category = new ItemCategory();
	}
	
	public Item(String name, String description, Integer quantity, ItemCategory category, LocalDate expiration,
			Clinic itemLocation) {
		super();
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.category = category;
		this.expiration = expiration;
		this.itemLocation = itemLocation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ItemCategory getCategory() {
		return category;
	}

	public void setCategory(ItemCategory category) {
		this.category = category;
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}

	public Clinic getItemLocation() {
		return itemLocation;
	}

	public void setItemLocation(Clinic itemLocation) {
		this.itemLocation = itemLocation;
	}
	
}