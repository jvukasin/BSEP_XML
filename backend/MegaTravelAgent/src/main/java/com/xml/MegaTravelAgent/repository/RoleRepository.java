package com.xml.MegaTravelAgent.repository;

import com.xml.MegaTravelAgent.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
	Role findByName(String name);
}
