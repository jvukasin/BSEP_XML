package com.xml.MegaTravelMBA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelMBA.model.temp.Role;


public interface RoleRepository extends JpaRepository<Role, String> {
	
	Role findByName(String name);

}
