package com.xmlbesp.MegaTravelPKI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xmlbesp.MegaTravelPKI.model.Certificate;


public interface CertificateRepository extends JpaRepository<Certificate, Long>, JpaSpecificationExecutor<Certificate> {
	
	Certificate findOneById(Long id);
}
