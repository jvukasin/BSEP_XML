package com.megatravel.accommodationservice.repository;

import com.megatravel.accommodationservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
	
	Role findByName(String name);

}
