package com.remd.spring.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.remd.spring.model.Clinic;
import com.remd.spring.model.PatientRecord;

// This will be AUTO IMPLEMENTED by Spring into a Bean called PatientRecordRepository
// CRUD refers Create, Read, Update, Delete
public interface PatientRecordRepository extends JpaRepository<PatientRecord, Integer>{
	List<PatientRecord> findAllByOrderByLastNameDesc();
	List<PatientRecord> findAllByOrderByLastNameAsc();
	List<PatientRecord> findByPatientClinic(Clinic currentClinic);
	List<PatientRecord> findByPatientClinicOrderByLastNameDesc(Clinic currentClinic);
	List<PatientRecord> findByPatientClinicOrderByLastNameAsc(Clinic currentClinic);
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE PatientRecord record SET record.firstName=?1, record.lastName=?2, record.gender=?3, record.contactNumber=?4"
			+ ", record.birthDate=?5, record.email=?6, record.homeAddress=?7, record.patientClinic=?8 where record.id=?9")
	int editRecordById(String firstName,String lastName, String gender, String contactNumber, LocalDate birthDate, String email,
			String homeAddress, Clinic clinic, Integer id);
}
