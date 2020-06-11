package com.remd.spring.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.remd.spring.model.Clinic;
import com.remd.spring.model.Item;
import com.remd.spring.model.ItemCategory;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item>findByCategoryIdAndIsActiveTrue(int categoryId);
	List<Item>findByIsActiveTrue();
	List<Item>findAllByItemLocationAndIsActiveTrue(Clinic userClinic);
	List<Item>findAllByCategoryAndIsActiveTrue(ItemCategory category);
	List<Item>findAllByItemLocationAndCategoryAndIsActiveTrue(Clinic userClinic, ItemCategory category);
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE Item item SET item.name=?1, item.description=?2, item.quantity=?3, item.category=?4, item.expiration=?5 WHERE item.id=?6")
	int editItemById(String name, String description, int quantity, ItemCategory category, LocalDate expiryDate, Integer id);
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE Item item SET item.isActive = ?1 WHERE item.id = ?2")
	int logicalDeleteItem(boolean isActive, Integer id);
}
