package com.xmlbesp.MegaTravelPKI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xmlbesp.MegaTravelPKI.model.Software;

public interface SoftwareRepository extends JpaRepository<Software, Long>, JpaSpecificationExecutor<Software> {
	
	Software findOneById(Long id);
}
