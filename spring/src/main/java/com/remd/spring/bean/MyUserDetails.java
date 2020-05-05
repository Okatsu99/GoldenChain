package com.remd.spring.bean;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
	private static final long serialVersionUID = 1001167367088737464L;
	private int id;
	private String userName;
	private String passWord;
	private boolean isActive;
	private List<GrantedAuthority> authorities;
	
	//Empty Constructor
	public MyUserDetails() {
		
	}
	
	public MyUserDetails(User user) {
		this.id = user.getId();
		this.userName = user.getUserName();
		this.passWord = user.getPassWord();
		this.isActive = user.isActive();
		this.authorities = Arrays.stream(user.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		System.out.println(this.authorities);
	}

	/* Returns a list of Granted Authorities */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.passWord;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}

}
