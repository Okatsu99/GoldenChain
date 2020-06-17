package com.remd.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.remd.spring.model.MedicinePrescription;

public interface MedicinePrescriptionRepository extends JpaRepository<MedicinePrescription, Integer> {
	@Query(value = "SELECT meds FROM MedicinePrescription meds WHERE patient.id=?1")
	List<MedicinePrescription> getAllPrescribedMedicinesOfPatient(Integer patientId);
}
