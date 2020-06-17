package com.remd.spring.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "patient_record")
public class PatientRecord {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "gender")
	private String gender;
	@Column(name = "contact_number")
	private String contactNumber;
	@Column(name = "birthdate", columnDefinition = "DATE")
	private LocalDate birthDate;
	@Column(name = "email")
	private String email;
	@Column(name = "home_address")
	private String homeAddress;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clinic_id", nullable = false)
	private Clinic patientClinic;
	@OneToMany(mappedBy = "record")
	private List<Appointment> appointments;
	@OneToMany(mappedBy = "patient")
	private List<MedicinePrescription> prescribedMedicines;

	public PatientRecord() {
		this.firstName = "";
		this.lastName = "";
		this.gender = "";
		this.contactNumber = "";
		this.birthDate = null;
		this.email = "";
		this.homeAddress = "";
		this.patientClinic = new Clinic();
		this.appointments = new ArrayList<Appointment>();
	}

	public PatientRecord(String firstName,
			String lastName,
			String gender,
			String contactNumber,
			LocalDate birthDate,
			String email,
			String homeAddress,
			Clinic patientClinic) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.birthDate = birthDate;
		this.email = email;
		this.homeAddress = homeAddress;
		this.patientClinic = patientClinic;
		this.appointments = null; // New Patient
	}

	public PatientRecord(String firstName,
			String lastName,
			String gender,
			String contactNumber,
			LocalDate birthDate,
			String email,
			String homeAddress,
			Clinic patientClinic,
			List<Appointment> appointments) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.birthDate = birthDate;
		this.email = email;
		this.homeAddress = homeAddress;
		this.patientClinic = patientClinic;
		this.appointments = appointments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Clinic getPatientClinic() {
		return patientClinic;
	}

	public void setPatientClinic(Clinic patientClinic) {
		this.patientClinic = patientClinic;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	/*
	 * Domain Functions
	 */
	public String getLongFormatBirthDate() {
		return this.birthDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
	}

	public String getFullNameStartingFirstName() {
		return this.firstName + " " + this.lastName;
	}

	public String getFullNameStartingLastName() {
		return this.lastName + ", " + this.firstName;
	}
}
