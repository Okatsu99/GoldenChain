package com.remd.spring.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.remd.spring.model.MedicinePrescription;
import com.remd.spring.model.MyUserDetails;
import com.remd.spring.model.User;
import com.remd.spring.services.EmailService;

@Controller
public class PrescriptionController {
	@Autowired
	EmailService emailService;
	@GetMapping(path = "/app/prescription")
	public String viewPage(Model model) {
		model.addAttribute("isPrescriptionActive", true);
		return "app/prescription"; 
	}
	@PostMapping(path = "/app/prescription/send")
	public String sendPrescriptionEmail(
			@RequestParam(name = "genericMedicineName")List<String> genericMedicines,
			@RequestParam(name = "brandedMedicineName")List<String> brandedMedicines,
			@RequestParam(name = "recommendedDosage")List<String> recommendedDosages,
			@RequestParam(name = "medicineNotes")List<String> medicineNotes,
			@RequestParam(name = "patientName")String patientName
			) throws MessagingException {
		Map<String, Object> params = new HashMap<String, Object>();
		List<MedicinePrescription> medicineList = new ArrayList<MedicinePrescription>();
		User currentUserDetails = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
		for (int i = 0; i < genericMedicines.size(); i++) {
			medicineList.add(new MedicinePrescription(genericMedicines.get(i), brandedMedicines.get(i), 
					recommendedDosages.get(i), medicineNotes.get(i)));
		}
		params.put("patientName", patientName);
		params.put("doctor", currentUserDetails);
		params.put("medicineList", medicineList);
		params.put("dateGenerated", LocalDate.now());
		emailService.sendPrescriptionEmailTemplate("someeobscuremailaddress@gmail.com", "test", params);
		return "redirect:/app/prescription";
	}
}
