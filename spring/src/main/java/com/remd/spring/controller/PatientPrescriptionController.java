package com.remd.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.remd.spring.model.MedicinePrescription;
import com.remd.spring.model.PatientRecord;
import com.remd.spring.repository.MedicinePrescriptionRepository;
import com.remd.spring.repository.PatientRecordRepository;

@Controller
public class PatientPrescriptionController {
	@Autowired
	private PatientRecordRepository patientRepository;
	@Autowired
	private MedicinePrescriptionRepository medicinePrescriptionRepository;
	@GetMapping(value = "/app/record/prescription")
	public String viewPatientPrescription(Model model, @RequestParam("patientId") Integer patientId) {
		PatientRecord record = patientRepository.findById(patientId)
				.orElseThrow(() -> new RuntimeException("Patient not found"));
		List<MedicinePrescription> medicineList = medicinePrescriptionRepository.getAllPrescribedMedicinesOfPatient(patientId);
		model.addAttribute("patient", record);
		model.addAttribute("medicineList", medicineList);
		return "app/patient-prescription";
	}
}
