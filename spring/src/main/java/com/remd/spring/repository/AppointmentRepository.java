package com.remd.spring.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.remd.spring.model.Appointment;
import com.remd.spring.model.Clinic;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	List<Appointment> findAllByOrderByTimeSlotAsc();
	@Query(value = "SELECT a FROM Appointment a INNER JOIN PatientRecord record ON a.record = record.id WHERE record.patientClinic=?1 ORDER BY a.timeSlot ASC")
	List<Appointment> getRecordsByClinic(Clinic clinic);
}
