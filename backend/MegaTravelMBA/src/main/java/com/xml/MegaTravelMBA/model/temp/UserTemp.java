package com.xml.MegaTravelMBA.model.temp;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class UserTemp {
	
	 @Id
	 @Column(name = "username", nullable = false)
	 private String username;
	 
	 @Column(name = "firstName")
	 private String firstName;
	 
	 @Column(name = "lastName")
	 private String lastName;
	 
	 @Column(name = "email")
	 private String email;
	 
	 @Column(name = "enabled")
	 private boolean enabled;
	 
	 @Column(name = "password")
	 private String password;
	 
	 @Column(name = "last_password_reset_date")
	    private Timestamp lastPasswordResetDate;
	 
	 @ManyToMany
	    @JoinTable( 
	        name = "users_roles", 
	        joinColumns =  @JoinColumn(
	          name = "user_id", referencedColumnName = "username"), 
	        inverseJoinColumns = @JoinColumn(
	        	name = "role_id", referencedColumnName = "id")) 
	 private Collection<Role> roles;
	 

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Timestamp getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}
	
	
	
}
