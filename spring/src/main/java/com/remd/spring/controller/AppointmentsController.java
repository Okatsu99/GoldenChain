package com.remd.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.remd.spring.model.Appointment;
import com.remd.spring.model.MyUserDetails;
import com.remd.spring.model.PatientRecord;
import com.remd.spring.repository.AppointmentRepository;
import com.remd.spring.repository.PatientRecordRepository;
import com.remd.spring.repository.RoleRepository;

@Controller
public class AppointmentsController {
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private PatientRecordRepository patientRecordRepository;
	@Autowired
	private RoleRepository roleRepository;
	@GetMapping("/app/appointments")
	public String viewAppointments(
			Model model,
			HttpServletRequest request) {
		MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		List<Appointment> appointments = null;
		List<PatientRecord> records = null;
		model.addAttribute("isAppointmentActive", true);
		if(request.isUserInRole(roleRepository.findById(1).get().getName())) {
			appointments = appointmentRepository.findAll();
			records = patientRecordRepository.findAll();
		}else {
			appointments = appointmentRepository.getRecordsByClinic(currentUser.getUser().getClinic());
			records = patientRecordRepository.findByPatientClinic(((currentUser.getUser().getClinic())));
		}
		model.addAttribute("appointments", appointments);
		model.addAttribute("patients", records);
		return "app/appointments";
	}
}
