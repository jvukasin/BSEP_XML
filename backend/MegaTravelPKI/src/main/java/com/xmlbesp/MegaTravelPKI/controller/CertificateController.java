package com.xmlbesp.MegaTravelPKI.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xmlbesp.MegaTravelPKI.dto.CertificateDTO;
import com.xmlbesp.MegaTravelPKI.dto.CertificateInfoDTO;
import com.xmlbesp.MegaTravelPKI.dto.RevocationDTO;
import com.xmlbesp.MegaTravelPKI.model.Certificate;
import com.xmlbesp.MegaTravelPKI.model.Software;
import com.xmlbesp.MegaTravelPKI.service.AdminPKIService;
import com.xmlbesp.MegaTravelPKI.service.CertificateService;
import com.xmlbesp.MegaTravelPKI.service.Logging;
import com.xmlbesp.MegaTravelPKI.service.SoftwareService;


@RestController
@RequestMapping(value="/certificate")
public class CertificateController {
	
	@Autowired
	CertificateService certificateService;
	
	@Autowired
	AdminPKIService adminPKIService;
	
	@Autowired
	SoftwareService softwareService;

	
	private Logging logger = new Logging(this);
	
	@RequestMapping(value = "/selfSignedExists", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean selfSignedExists() {
		return certificateService.selfSignedExists();
	}
	
	@RequestMapping(value = "/selfSigned", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CertificateDTO> getSelfSigned() {
		Certificate c = certificateService.getSelfSigned();
		
		if (c == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		if (c.isRevoked()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
		
		return new ResponseEntity<CertificateDTO>(new CertificateDTO(c), HttpStatus.OK);
	}  
	
	@RequestMapping(value = "/selfSigned", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> generateSelfSignedCertificate (@RequestBody CertificateInfoDTO certInfoDTO){

		if (certificateService.selfSignedExists()) {

			return new ResponseEntity<>("Self signed certificate already exists.", HttpStatus.BAD_REQUEST);
		}

		Certificate c = certificateService.generateSelfSignedCertificate(certInfoDTO);
		

		return new ResponseEntity<CertificateDTO>(new CertificateDTO(c), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/generateIssuedCertificate/{id}", method = RequestMethod.POST, consumes = "application" +
			"/json")
	public ResponseEntity<?> generateIssuedCertificate(@PathVariable Long id,
																	@RequestBody CertificateInfoDTO certInfoDTO) throws ParseException
	{
		System.out.println(certInfoDTO.getSubjectDataDTO().getUid());

		Software s = softwareService.findOneById(id);
		if (s == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}



		Certificate c = certificateService.generateIssuedCertificate(certInfoDTO);

		if (c == null) {

			return new ResponseEntity<>("Certificate can not be generated.", HttpStatus.BAD_REQUEST);
		}

		s.setCertified(true);
		s.setCertificate(c);
		softwareService.save(s);

		return new ResponseEntity<>(new CertificateDTO(c), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/revokeCertificate", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<?> revokeCertificate(@RequestBody RevocationDTO revocationDTO) throws ParseException
	{

		List<CertificateDTO> retVal = certificateService.revoke(revocationDTO);
		softwareService.updateSoftwares();
		logger.logInfo("Revoking certificate - END");
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllCertificates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CertificateDTO>> getAllCertificates() {
		
		List<Certificate> certificates = certificateService.findAll();
		List<CertificateDTO> cDTO = new ArrayList<>();
		
		for (Certificate c : certificates) {
			CertificateDTO cdto = new CertificateDTO(c);
			cDTO.add(cdto);
		}
		
		return new ResponseEntity<>(cDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllCAs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CertificateDTO>> getAllCAs() {
		
		List<Certificate> certificates = certificateService.findAll();
		List<CertificateDTO> cDTO = new ArrayList<>();
		
		for (Certificate c : certificates) {
			if(c.isCa() && !c.isRevoked()) {
				CertificateDTO cdto = new CertificateDTO(c);
				cDTO.add(cdto);
			}
		}
		
		return new ResponseEntity<>(cDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCertificate/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CertificateDTO> getSpecificCert(@PathVariable("id") Long id) {
		
		Certificate cert = certificateService.findOneById(id);
		
		if(cert == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new CertificateDTO(cert), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/validateCertificate/{id}", method = RequestMethod.GET)
	private String validate(@PathVariable("id") Long id){
		Certificate c = certificateService.findOneById(id);
		Certificate parent = certificateService.findOneById(c.getIdIssuer());
		
		return certificateService.validate(c, parent);
	}
}
