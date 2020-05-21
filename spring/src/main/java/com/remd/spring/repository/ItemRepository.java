package com.remd.spring.repository;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.remd.spring.model.Clinic;
import com.remd.spring.model.Item;
import com.remd.spring.model.ItemCategory;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	Set<Item>findByCategoryId(int categoryId);
	Set<Item>findAllByItemLocation(Clinic userClinic);
	Set<Item>findAllByCategory(ItemCategory category);
	Set<Item>findAllByItemLocationAndCategory(Clinic userClinic, ItemCategory category);
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE item_list item SET item.name=?1, item.description=?2, item.quantity=?3, category=?4, item.expiration=?5 WHERE item.id=?6")
	int editItemById(String name, String description, int quantity, ItemCategory category, LocalDate expiryDate, Integer id);
}
