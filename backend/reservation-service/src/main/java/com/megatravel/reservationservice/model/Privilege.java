package com.megatravel.reservationservice.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Privilege implements GrantedAuthority{
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @Column(name = "name")
    private String name;
 
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
    
	public Long getId() {
		return id;
	}
    
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String getAuthority() {
		return name;
	}
}