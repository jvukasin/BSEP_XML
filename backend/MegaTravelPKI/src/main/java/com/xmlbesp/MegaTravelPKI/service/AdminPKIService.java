package com.xmlbesp.MegaTravelPKI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmlbesp.MegaTravelPKI.model.AdminPKI;
import com.xmlbesp.MegaTravelPKI.repository.AdminPKIRepository;

@Service
public class AdminPKIService {
	
	@Autowired
	AdminPKIRepository adminPKIRepo;
	
	public List<AdminPKI> findAll(){
		return adminPKIRepo.findAll();
	}
	
}
