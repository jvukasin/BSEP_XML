package com.megatravel.authservice.repository;

import com.megatravel.authservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
	
	Role findByName(String name);

}
