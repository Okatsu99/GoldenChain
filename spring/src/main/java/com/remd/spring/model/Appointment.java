package com.remd.spring.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "time_slot")
	private LocalDateTime timeSlot;
	@Column(name = "doctor_notes")
	private String doctorNotes;
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private PatientRecord record;
	
	public Appointment() {
		this.id = 0;
		this.timeSlot = LocalDateTime.now();
		this.doctorNotes = "";
		this.record = new PatientRecord();
	}
	
	public Appointment(LocalDateTime timeSlot,String doctorNotes, PatientRecord record) {
		this.timeSlot = timeSlot;
		this.doctorNotes = doctorNotes;
		this.record = record;
	}
	
	public Appointment(LocalDate date, LocalTime time,String doctorNotes, PatientRecord record) {
		this.timeSlot = LocalDateTime.of(date, time);
		this.doctorNotes = doctorNotes;
		this.record = record;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(LocalDateTime timeSlot) {
		this.timeSlot = timeSlot;
	}
	public void setTimeSlot(LocalDate date, LocalTime time) {
		this.timeSlot = LocalDateTime.of(date, time);
	}
	public PatientRecord getRecord() {
		return record;
	}
	public void setRecord(PatientRecord record) {
		this.record = record;
	}
	
	public String getDoctorNotes() {
		return doctorNotes;
	}

	public void setDoctorNotes(String doctorNotes) {
		this.doctorNotes = doctorNotes;
	}

	public String getAppointmentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
		return this.timeSlot.format(dtf);
	}
	public LocalTime getAppointmentTime() {
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");
		return this.timeSlot.toLocalTime();
	}
}
