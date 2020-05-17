package com.remd.spring.bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity(name = "clinics")
public class Clinic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "location")
	private String location;
	@OneToMany(mappedBy = "patientClinic")
	private Set<PatientRecord> record;
	@OneToMany(mappedBy = "")
	private Set<User> secretaries;
	public Clinic() {
		this.name = "";
		this.location = "";
		this.record = null;
	}
	
	public Clinic(String name, String location) {
		this.name = name;
		this.location = location;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Set<PatientRecord> getRecord() {
		return record;
	}
	public void setRecord(Set<PatientRecord> record) {
		this.record = record;
	}
	
}
