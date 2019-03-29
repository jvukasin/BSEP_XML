package com.xmlbesp.MegaTravelPKI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmlbesp.MegaTravelPKI.model.Certificate;
import com.xmlbesp.MegaTravelPKI.repository.CertificateRepository;

@Service
public class CertificateService {
	
	@Autowired
	CertificateRepository certRepo;
	
	public Certificate findOneById(Long id) {
		return certRepo.findOneById(id);
	}
	
	public List<Certificate> findAll() {
		return certRepo.findAll();
	}
	
	public Certificate save(Certificate cert) {
		return certRepo.save(cert);
	}
}
