package com.remd.spring.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.remd.spring.model.Appointment;
import com.remd.spring.model.Clinic;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	List<Appointment> findAllByOrderByTimeSlotAsc();
	@Query(value = "SELECT a FROM Appointment a INNER JOIN PatientRecord record ON a.record = record.id WHERE record.patientClinic=?1 ORDER BY a.timeSlot ASC")
	List<Appointment> getRecordsByClinic(Clinic clinic);
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE Appointment appointment SET appointment.timeSlot=?1, appointment.doctorNotes=?2 WHERE appointment.id=?3")
	int editAppointmentById(LocalDateTime timeSlot,String doctorNotes, Integer appointmentId);
}
