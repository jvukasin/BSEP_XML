package com.xmlbesp.MegaTravelPKI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmlbesp.MegaTravelPKI.model.Software;
import com.xmlbesp.MegaTravelPKI.repository.SoftwareRepository;

@Service
public class SoftwareService {
	
	@Autowired
	SoftwareRepository softRepo;
	
	public Software findOneById(Long id) {
		return softRepo.findOneById(id);
	}
	
	public List<Software> findAll() {
		return softRepo.findAll();
	}
	
	public Software save(Software soft) {
		return softRepo.save(soft);
	}

}
