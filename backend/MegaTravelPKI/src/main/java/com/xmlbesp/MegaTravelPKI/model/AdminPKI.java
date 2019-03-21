package com.xmlbesp.MegaTravelPKI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AdminPKI {
	
	@Id
	@Column(name="username", nullable = false)
	String username;
	
	@Column(name="password",nullable = false)
	String password;

	public AdminPKI() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
