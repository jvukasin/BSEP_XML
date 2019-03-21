package com.xmlbesp.MegaTravelPKI.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xmlbesp.MegaTravelPKI.dto.CertificateDTO;
import com.xmlbesp.MegaTravelPKI.model.Certificate;
import com.xmlbesp.MegaTravelPKI.service.CertificateService;


@RestController
@RequestMapping(value="/certificate")
public class CertificateController {
	
	@Autowired
	CertificateService certificateService;
	
	@RequestMapping(value = "/selfSigned", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean selfSignedExists() {
		
		List<Certificate> certifs = certificateService.findAll();
		for(Certificate c : certifs) {
			if(c.isCa()) return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/generateSelfSigned",method = RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<CertificateDTO> generateSelfSigned(@RequestBody CertificateDTO certDTO) throws ParseException
	{
		
		return null;
	}
}
