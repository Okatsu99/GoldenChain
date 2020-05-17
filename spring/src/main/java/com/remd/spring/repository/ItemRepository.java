package com.remd.spring.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.remd.spring.bean.Item;
import com.remd.spring.bean.ItemCategory;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item>findByCategoryId(int category);
	
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE item_list item SET item.name=?1, item.description=?2, item.quantity=?3, category=?4, item.expiration=?5 WHERE item.id=?6")
	int editItemById(String name, String description, int quantity, ItemCategory category, LocalDate expiryDate, Integer id);
}
