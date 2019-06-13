package com.megatravel.reservationservice.repository;

import com.megatravel.reservationservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
	
	Role findByName(String name);

}
