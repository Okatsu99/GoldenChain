package com.remd.spring.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "username")
	private String userName;
	@Column(name = "password")
	private String passWord;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "join_user_role",
		joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName="id"))
	private Collection<Role> roles;
	@Column(name = "is_account_enabled")
	private boolean isAccountEnabled;
	@Column(name = "is_credentials_non_expired")
	private boolean isCredentialsNonExpired;
	@Column(name = "is_account_non_locked")
	private boolean isAccountNonLocked;
	@Column(name = "is_account_non_expired")
	private boolean isAccountNonExpired;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "cellphoneNumber")
	private String cellphoneNumber;
	@Column(name = "license_number")
	private String licenseNumber;
	@Column(name = "ptr_number")
	private String ptrNumber;
	@ManyToOne
	@JoinColumn(name = "clinic_id")
	private Clinic clinic;
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private User doctor;
	@OneToMany(mappedBy = "doctor")
	private Set<User> secretaries;
	//Default
	public User() {
		this.userName = "";
		this.passWord = "";
		this.isAccountEnabled = false;
		this.isCredentialsNonExpired = false;
		this.isAccountNonLocked = false;
		this.isAccountNonExpired = false;
		this.roles = null;
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.cellphoneNumber = "";
		this.licenseNumber = "";
		this.ptrNumber = "";
		this.clinic = null;
		this.doctor = null;
		this.secretaries = null;
	}
	//For Doctor Creation
	public User(String userName, String passWord, Collection<Role> roles, boolean isAccountEnabled,
			boolean isCredentialsNonExpired, boolean isAccountNonLocked, boolean isAccountNonExpired, String firstName,
			String lastName, String email, String cellphoneNumber, String licenseNumber, String ptrNumber) {
		this.userName = userName;
		this.passWord = passWord;
		this.roles = roles;
		this.isAccountEnabled = isAccountEnabled;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isAccountNonExpired = isAccountNonExpired;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.cellphoneNumber = cellphoneNumber;
		this.licenseNumber = licenseNumber;
		this.ptrNumber = ptrNumber;
		//defaults
		this.clinic = null;
		this.doctor = null;
		this.secretaries = null;
	}
	
	public User(String userName, String passWord, Collection<Role> roles, boolean isAccountEnabled,
			boolean isCredentialsNonExpired, boolean isAccountNonLocked, boolean isAccountNonExpired, String firstName,
			String lastName, String email, String cellphoneNumber, Clinic clinic, User doctor) {
		this.userName = userName;
		this.passWord = passWord;
		this.roles = roles;
		this.isAccountEnabled = isAccountEnabled;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isAccountNonExpired = isAccountNonExpired;
		this.email = email;
		this.cellphoneNumber = cellphoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.cellphoneNumber = cellphoneNumber;
		this.clinic = clinic;
		this.doctor = doctor;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	public boolean isAccountEnabled() {
		return isAccountEnabled;
	}
	public void setAccountEnabled(boolean isAccountEnabled) {
		this.isAccountEnabled = isAccountEnabled;
	}
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}
	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}
	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCellphoneNumber() {
		return cellphoneNumber;
	}
	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getPtrNumber() {
		return ptrNumber;
	}
	public void setPtrNumber(String ptrNumber) {
		this.ptrNumber = ptrNumber;
	}
	public Clinic getClinic() {
		return clinic;
	}
	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}
	public User getDoctor() {
		return doctor;
	}
	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}
	public Set<User> getSecretaries() {
		return secretaries;
	}
	public void setSecretaries(Set<User> secretaries) {
		this.secretaries = secretaries;
	}
	/*
	 * Domain Functions
	 */
	public String getFullNameFormatted() {
		return firstName + " " + lastName;
	}
}
