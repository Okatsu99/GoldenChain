package com.remd.spring.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.remd.spring.model.Procedure;

public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {
	@Query("SELECT p.price FROM Procedure p WHERE p.id = ?1")
	Optional<BigDecimal> findPriceById(Integer id);
}
