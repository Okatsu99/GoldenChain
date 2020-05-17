package com.remd.spring.controller.configuration;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.remd.spring.bean.Clinic;
import com.remd.spring.bean.MyUserDetails;
import com.remd.spring.bean.User;
import com.remd.spring.controller.AppointmentsController;
import com.remd.spring.controller.InventoryController;
import com.remd.spring.controller.PatientRecordController;
import com.remd.spring.controller.PrescriptionController;
import com.remd.spring.controller.ReceiptController;
import com.remd.spring.repository.ClinicRepository;
import com.remd.spring.repository.UserRepository;

@ControllerAdvice(
		assignableTypes = {AppointmentsController.class, InventoryController.class,
				PatientRecordController.class,PrescriptionController.class,ReceiptController.class})
public class MyControllerAdvice {
	@Autowired
	ClinicRepository clinicRepository;
	@Autowired
	UserRepository userRepository;
	@ModelAttribute("profile")
	public MyUserDetails getCurrentUser() {
		return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	@ModelAttribute("clinicList")
	public List<Clinic> getClinics(){
		return clinicRepository.findAll();
	}
	@ModelAttribute("secretaryList")
	public List<User> getSecretaries(){
		return userRepository.findByDoctorId(getCurrentUser().getUser().getId());
	}
}
