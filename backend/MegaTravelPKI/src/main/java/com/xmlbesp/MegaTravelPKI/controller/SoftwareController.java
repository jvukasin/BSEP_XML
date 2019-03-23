package com.xmlbesp.MegaTravelPKI.controller;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xmlbesp.MegaTravelPKI.dto.CommunicationDTO;
import com.xmlbesp.MegaTravelPKI.dto.SoftwareDTO;
import com.xmlbesp.MegaTravelPKI.keystores.KeyStoreReader;
import com.xmlbesp.MegaTravelPKI.keystores.KeyStoreWriter;
import com.xmlbesp.MegaTravelPKI.model.Certificate;
import com.xmlbesp.MegaTravelPKI.model.Communication;
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
	
	
	// idea 
	@RequestMapping(value = "/openCommunication", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> openCommunication(@RequestBody CommunicationDTO communicationDTO) {
		
		
		Software softwareA = softwareService.findOneById(communicationDTO.getSoftwareAId());
		Software softwareB = softwareService.findOneById(communicationDTO.getSoftwareBId());
		KeyStoreWriter ksWriterA = new KeyStoreWriter();
		KeyStoreWriter ksWriterB = new KeyStoreWriter();
		KeyStoreReader ksReaderA = new KeyStoreReader();
		KeyStoreReader ksReaderB = new KeyStoreReader();
		
		// temp 
		String passwordPrefix = "issuedCertPass";
		
		String aliasA = softwareA.getName() + softwareA.getId();
		String aliasB = softwareB.getName() + softwareB.getId();
		
		
		String localAlias="myCertificate";
		
		PrivateKey privateKeyA = ksReaderA.readPrivateKey(aliasA + "KeyStore", aliasA, localAlias, localAlias);
		PrivateKey privateKeyB = ksReaderA.readPrivateKey(aliasB + "KeyStore", aliasB, localAlias, localAlias);
		
		
		java.security.cert.Certificate certificateA = ksReaderA.readCertificate(aliasA + "KeyStore", aliasA, localAlias);
		java.security.cert.Certificate certificateB = ksReaderB.readCertificate(aliasB + "KeyStore", aliasB, localAlias);
		
		Communication com = new Communication(communicationDTO.getSoftwareAId(), communicationDTO.getSoftwareBId());
		
		// TODO communication service: save to database
		
		ksWriterA.loadKeyStore(aliasA + "KeyStore", aliasA.toCharArray());
		ksWriterB.loadKeyStore(aliasB + "KeyStore", aliasB.toCharArray());
		
		ksWriterA.write(aliasA + "KeyStore", privateKeyB, aliasB.toCharArray(), certificateB);
		ksWriterA.saveKeyStore(aliasA + "KeyStore", aliasA.toCharArray());

		ksWriterB.write(aliasA + "KeyStore", privateKeyA, aliasA.toCharArray(), certificateA);
		ksWriterB.saveKeyStore(aliasB + "KeyStore", aliasB.toCharArray());
		
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
	
}

