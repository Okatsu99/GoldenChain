package com.remd.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remd.spring.bean.PatientRecord;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface PatientRecordRepository extends JpaRepository<PatientRecord, Integer>{
	List<PatientRecord> findAllByOrderByLastNameDesc();
	List<PatientRecord> findAllByOrderByLastNameAsc();
}
