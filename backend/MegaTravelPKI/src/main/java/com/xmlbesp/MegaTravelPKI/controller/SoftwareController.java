package com.xmlbesp.MegaTravelPKI.controller;

import java.security.KeyStoreException;
import java.util.ArrayList;
import java.util.List;

import com.xmlbesp.MegaTravelPKI.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.xmlbesp.MegaTravelPKI.dto.CommunicationDTO;
import com.xmlbesp.MegaTravelPKI.dto.SoftwareDTO;
import com.xmlbesp.MegaTravelPKI.model.Certificate;
import com.xmlbesp.MegaTravelPKI.model.Software;
import com.xmlbesp.MegaTravelPKI.service.SoftwareService;

@RestController
@RequestMapping(value="/software")
public class SoftwareController {
	
	@Autowired
	SoftwareService softwareService;

	@Autowired
	CertificateService certificateService;
	
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


	@RequestMapping(value = "/{id}/trusted", method = RequestMethod.GET)
	public ResponseEntity<List<SoftwareDTO>> getTrustedSoftware(@PathVariable Long id) {

		Software soft  = softwareService.findOneById(id);

		List<SoftwareDTO> retVal = new ArrayList<>();

		for (Software s: soft.getTrustedSoftwares()) {
			retVal.add(new SoftwareDTO(s));
		}

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	
	// idea 
	@RequestMapping(value = "/openCommunication", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> openCommunication(@RequestBody CommunicationDTO communicationDTO) {
		
		
		Software softwareA = softwareService.findOneById(communicationDTO.getSoftwareAId());
		Software softwareB = softwareService.findOneById(communicationDTO.getSoftwareBId());

		if (!softwareA.isCertified() || !softwareB.isCertified()) {

			return new ResponseEntity<>("One of the softwares are not certified", HttpStatus.BAD_REQUEST);
		}

		try {
			certificateService.formTrustStores(softwareA.getCertificate(), softwareB.getCertificate());
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}


		softwareA.getTrustedSoftwares().add(softwareB);
		softwareB.setSoftware(softwareA);

		softwareB.getTrustedSoftwares().add(softwareA);
		softwareA.setSoftware(softwareB);

		softwareService.save(softwareA);
		softwareService.save(softwareB);

		return new ResponseEntity<>(HttpStatus.OK);
	}


	
	
}

