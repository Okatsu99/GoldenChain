package com.remd.spring.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "item_category")
public class ItemCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "category")
	private String categoryName;
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Item> item;
	
	public ItemCategory() {
		this.categoryName = "";
		item = null;
	}
	public ItemCategory(int id, String categoryName, List<Item> item) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.item = item;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}
	
}
