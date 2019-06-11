package com.megatravel.authservice.repository;

import com.megatravel.authservice.model.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
	
	Role findByName(String name);

}
