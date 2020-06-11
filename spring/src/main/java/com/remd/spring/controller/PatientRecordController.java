package com.remd.spring.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.remd.spring.model.MyUserDetails;
import com.remd.spring.model.PatientRecord;
import com.remd.spring.repository.ClinicRepository;
import com.remd.spring.repository.PatientRecordRepository;
import com.remd.spring.repository.RoleRepository;

@Controller
public class PatientRecordController {
	// This means to get the bean called PatientRecordRepository
    // Which is auto-generated by Spring, we will use it to handle the data
	@Autowired
	private ClinicRepository clinicRepository;
	@Autowired
	private PatientRecordRepository patientRecordRepository;
	@Autowired
	private RoleRepository roleRepository;
	@GetMapping(path = "/app/patientrecords")
	public String viewPatientRecords(
			Model model,
			HttpServletRequest request) {
		MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("record", new PatientRecord());
		model.addAttribute("isPatientRecordsActive", true);
		List<PatientRecord> records;
		/*
		 * Current DB has only 1 = Doctor and 2 = Secretary
		 */
		if (request.isUserInRole(roleRepository.findById(1).get().getName())) {
			records = patientRecordRepository.findAll();
			Collections.reverse(records);
		} else {
			records = patientRecordRepository.findByPatientClinic(((currentUser.getUser().getClinic())));
			Collections.reverse(records);
		}
		model.addAttribute("patientRecords",  records);
		return "app/patientrecords";
	}
	@GetMapping(path = "/app/patientrecords", params = {"filter"})
	public String viewRecordsSorted(@RequestParam("filter")Integer Sort) {
		
		return "app/patientrecords :: recordsTableBody";
	}
	@GetMapping(path = "/app/patientrecords/record/view/{id}")
	public String viewRecord(@PathVariable("id") Integer id, Model model) {
		PatientRecord record = patientRecordRepository.findById(id).get();
		model.addAttribute("record", record);
		return "app/patientrecords :: editPersonModalContent";
	}
	@PostMapping(path = "/app/patientrecords/new")
	public String insertRecord(
			@RequestParam(name = "patientFirstName")String firstName,
			@RequestParam(name = "patientLastName")String lastName,
			@RequestParam(name = "patientGender")String gender,
			@RequestParam(name = "patientContactNumber")String contactNumber,
			@RequestParam(name = "patientBirthDate")
				@DateTimeFormat(iso = ISO.DATE)LocalDate birthDate,
			@RequestParam(name = "patientEmailAddress")String email,
			@RequestParam(name = "patientHomeAddress")String homeAddress,
			@RequestParam(name = "clinicId")Integer clinicId
			) {
		PatientRecord record = new PatientRecord(firstName,lastName,gender,contactNumber,
				birthDate, email, homeAddress,clinicRepository.findById(clinicId).get());
		patientRecordRepository.saveAndFlush(record);
		return "redirect:/app/patientrecords";
	}
	@PostMapping(path = "/app/patientrecords/edit")
	public String editRecord(
			@RequestParam(name = "patientId")Integer patientId,
			@RequestParam(name = "patientFirstName")String firstName,
			@RequestParam(name = "patientLastName")String lastName,
			@RequestParam(name = "patientGender")String gender,
			@RequestParam(name = "patientContactNumber")String contactNumber,
			@RequestParam(name = "patientBirthDate")
				@DateTimeFormat(iso = ISO.DATE)LocalDate birthDate,
			@RequestParam(name = "patientEmailAddress")String email,
			@RequestParam(name = "patientHomeAddress")String homeAddress,
			@RequestParam(name = "clinicId")Integer clinicId
			) {
		patientRecordRepository.editRecordById(firstName, lastName, gender, contactNumber, birthDate, email, homeAddress, clinicRepository.findById(clinicId).get(), patientId);
		return "redirect:/app/patientrecords";
	}
}
