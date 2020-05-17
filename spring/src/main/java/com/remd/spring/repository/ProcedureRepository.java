package com.remd.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remd.spring.bean.Procedure;

public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {

}
