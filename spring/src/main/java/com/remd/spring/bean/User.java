package com.remd.spring.bean;

import java.util.Collection;
import java.util.List;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

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
	@ManyToOne
	@JoinColumn(name = "clinic_id")
	private Clinic clinic;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "doctor_id")
	private User doctor;
	@OneToMany(mappedBy = "doctor")
	private Set<User> secretaries;

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
		this.clinic = null;
		this.doctor = null;
		this.secretaries = null;
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

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
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
