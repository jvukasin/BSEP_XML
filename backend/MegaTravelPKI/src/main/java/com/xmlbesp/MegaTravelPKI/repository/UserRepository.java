package com.xmlbesp.MegaTravelPKI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xmlbesp.MegaTravelPKI.model.Software;
import com.xmlbesp.MegaTravelPKI.model.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	
	User findOneById(Long id);
	
}
