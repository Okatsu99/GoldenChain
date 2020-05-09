package com.remd.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remd.spring.bean.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
}
