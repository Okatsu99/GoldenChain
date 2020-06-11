package com.remd.spring.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private PatientRecord record;
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
	public LocalDate getAppointmentDate() {
		return this.timeSlot.toLocalDate();
	}
	public LocalTime getAppointmentTime() {
		return this.timeSlot.toLocalTime();
	}
}
