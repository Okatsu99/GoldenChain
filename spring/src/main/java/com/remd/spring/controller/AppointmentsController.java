package com.remd.spring.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String viewAppointments(Model model, HttpServletRequest request) {
		MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		List<Appointment> appointments = null;
		List<PatientRecord> records = null;
		Appointment appointment = new Appointment();
		model.addAttribute("isAppointmentActive", true);
		if (request.isUserInRole(roleRepository.findById(1).get().getName())) {
			appointments = appointmentRepository.findAllByOrderByTimeSlotAsc();
			records = patientRecordRepository.findAll();
		} else {
			appointments = appointmentRepository.getRecordsByClinic(currentUser.getUser().getClinic());
			records = patientRecordRepository.findByPatientClinic(((currentUser.getUser().getClinic())));
		}
		model.addAttribute("appointments", appointments);
		model.addAttribute("patients", records);
		model.addAttribute("appointmentData", appointment);
		return "app/appointments";
	}

	@PostMapping("/app/appointments/new")
	public String addAppointment(@RequestParam(name = "patientRecordId") Integer patientRecordId,
			@RequestParam(name = "appointmentDate") @DateTimeFormat(iso = ISO.DATE) LocalDate appointmentDate,
			@RequestParam(name = "appointmentTime") @DateTimeFormat(iso = ISO.TIME) LocalTime appointmentTime,
			@RequestParam(name = "appointmentDoctorNotes") String appointDoctorNotes) {
		Appointment appointment = new Appointment(appointmentDate, appointmentTime, appointDoctorNotes,
				patientRecordRepository.findById(patientRecordId).get());
		appointmentRepository.saveAndFlush(appointment);
		return "redirect:/app/appointments";
	}

	@PostMapping("/app/appointments/delete")
	public String deleteAppointment(
			@RequestParam(name = "selectAppointment") List<Integer> appointmentIDs
			) {
		for (int i = 0; i < appointmentIDs.size(); i++) {
			appointmentRepository.deleteById(appointmentIDs.get(i));;
		}
		return "redirect:/app/appointments";
	}
	
	@GetMapping("/app/appointments/view/{id}")
	public String getViewAppointmentModal(
			@PathVariable("id")Integer appointmentId
			) {
		return "app/appointments :: appointmentModal";
	}
}
