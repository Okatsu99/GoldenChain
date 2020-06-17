package com.remd.spring.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prescribed_medicines")
public class MedicinePrescription {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "general_name")
	private String generalMedicineName;
	@Column(name = "branded_name")
	private String brandedMedicineName;
	@Column(name = "recommended_dosage")
	private String recommendedDosage;
	@Column(name = "medicine_notes")
	private String medicineNotes;
	@Column(name = "date_generated")
	LocalDate dateGenerated;
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private PatientRecord patient;

	public MedicinePrescription() {
		this.generalMedicineName = "";
		this.brandedMedicineName = "";
		this.recommendedDosage = "";
		this.medicineNotes = "";
		this.dateGenerated = LocalDate.now();
		this.patient = new PatientRecord();
	}

	public MedicinePrescription(String generalMedicineName,
			String brandedMedicineName,
			String recommendedDosage,
			String medicineNotes,
			LocalDate dateGenerated,
			PatientRecord patient) {
		this.generalMedicineName = generalMedicineName;
		this.brandedMedicineName = brandedMedicineName;
		this.recommendedDosage = recommendedDosage;
		this.medicineNotes = medicineNotes;
		this.dateGenerated = dateGenerated;
		this.patient = patient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGeneralMedicineName() {
		return generalMedicineName;
	}

	public void setGeneralMedicineName(String generalMedicineName) {
		this.generalMedicineName = generalMedicineName;
	}

	public String getBrandedMedicineName() {
		return brandedMedicineName;
	}

	public void setBrandedMedicineName(String brandedMedicineName) {
		this.brandedMedicineName = brandedMedicineName;
	}

	public String getRecommendedDosage() {
		return recommendedDosage;
	}

	public void setRecommendedDosage(String recommendedDosage) {
		this.recommendedDosage = recommendedDosage;
	}

	public String getMedicineNotes() {
		return medicineNotes;
	}

	public void setMedicineNotes(String medicineNotes) {
		this.medicineNotes = medicineNotes;
	}

	public LocalDate getDateGenerated() {
		return dateGenerated;
	}

	public void setDateGenerated(LocalDate dateGenerated) {
		this.dateGenerated = dateGenerated;
	}

	public PatientRecord getPatient() {
		return patient;
	}

	public void setPatient(PatientRecord patient) {
		this.patient = patient;
	}

	/*
	 * Domain Functions
	 */
	public String getShortFormatDateGenerated() {
		return this.dateGenerated.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
	}
}
