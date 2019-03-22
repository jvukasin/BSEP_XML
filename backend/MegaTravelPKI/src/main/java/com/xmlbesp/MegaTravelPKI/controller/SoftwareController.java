package com.xmlbesp.MegaTravelPKI.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xmlbesp.MegaTravelPKI.dto.SoftwareDTO;
import com.xmlbesp.MegaTravelPKI.model.Certificate;
import com.xmlbesp.MegaTravelPKI.model.Software;
import com.xmlbesp.MegaTravelPKI.service.SoftwareService;

@RestController
@RequestMapping(value="/software")
public class SoftwareController {
	
	@Autowired
	SoftwareService softwareService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<SoftwareDTO>> getAllSoftware() {
		
		List<Software> softwares = softwareService.findAll();
		List<SoftwareDTO> sftDTO = new ArrayList<>();
		
		for (Software s : softwares) {
			if(s.getCertificate() == null) {
				Certificate c = new Certificate();
				s.setCertificate(c);
			}
			SoftwareDTO sDTO = new SoftwareDTO(s);
			sftDTO.add(sDTO);
			
		}
		
		return new ResponseEntity<>(sftDTO, HttpStatus.OK);
	}
}
