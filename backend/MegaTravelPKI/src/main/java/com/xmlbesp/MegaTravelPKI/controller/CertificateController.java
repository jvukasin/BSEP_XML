package com.xmlbesp.MegaTravelPKI.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.xmlbesp.MegaTravelPKI.dto.SoftwareDTO;
import com.xmlbesp.MegaTravelPKI.dto.SubjectDataDTO;
import com.xmlbesp.MegaTravelPKI.keystores.KeyStoreReader;
import com.xmlbesp.MegaTravelPKI.model.Certificate;
import com.xmlbesp.MegaTravelPKI.model.Software;
import com.xmlbesp.MegaTravelPKI.service.AdminPKIService;
import com.xmlbesp.MegaTravelPKI.service.CertificateService;
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
	
	@RequestMapping(value = "/selfSignedExists", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean selfSignedExists() {
		System.out.println("ping");
		return certificateService.selfSignedExists();
	}
	
	@RequestMapping(value = "/selfSigned", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CertificateDTO> getSelfSigned() {
		Certificate c = certificateService.getSelfSigned();
		
		if (c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<CertificateDTO>(new CertificateDTO(c), HttpStatus.OK);
	}  
	
	@RequestMapping(value = "/selfSigned", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CertificateDTO> generateSelfSignedCertificate (@RequestBody CertificateInfoDTO certInfoDTO){ 
		
		Certificate c = certificateService.generateSelfSignedCertificate(certInfoDTO.getStartDate(), certInfoDTO.getEndDate());
		

		return new ResponseEntity<CertificateDTO>(new CertificateDTO(c), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/generateIssuedCertificate", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<CertificateDTO> generateIssuedCertificate(@RequestBody CertificateInfoDTO certInfoDTO) throws ParseException
	{
		System.out.println(certInfoDTO.getSubjectDataDTO().getUid());
		Certificate c = certificateService.generateIssuedCertificate(certInfoDTO);
		
		return new ResponseEntity<>(new CertificateDTO(c), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/revokeCertificate", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<SoftwareDTO> revokeCertificate(@RequestBody RevocationDTO revocationDTO) throws ParseException
	{
		
		Software software = softwareService.findOneById(revocationDTO.getSubjectId());
		
		if (software.isCertified() && software.getCertificate() != null) {
			software.getCertificate().setRevoked(true);
			software.getCertificate().setReasonForRevocation(revocationDTO.getReasonForRevocation());
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		software = softwareService.save(software);
		
		return new ResponseEntity<>(new SoftwareDTO(software), HttpStatus.OK);
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
			if(c.isCa()) {
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
	
	@RequestMapping(value = "/validateCertificate/{idSoft}", method = RequestMethod.GET)
	private boolean validate(@PathVariable("idSoft") Long id){
		Software s = softwareService.findOneById(id);
		Certificate cert = s.getCertificate();
		
		Calendar thisMoment = Calendar.getInstance();
		thisMoment.set(Calendar.HOUR_OF_DAY, 0);
		thisMoment.set(Calendar.MINUTE, 0);
		thisMoment.set(Calendar.SECOND, 0);
		
		//napraviti Date objekat kako bi moglo da se poredi
		Date today = thisMoment.getTime();
		if(cert.isRevoked()) {
			return false;
		}else if(today.after(cert.getEndDate())) {
			return false;
		}else{
			//provera digitalnog potpisa
			//treba uzeti sertifikat softvera i publickey admina iz globalKeyStore
			KeyStoreReader keyStoreReader = new KeyStoreReader();
			//uzimam sertifikat softvera
			java.security.cert.Certificate certificateSoft = keyStoreReader.readCertificate("globalKeyStore", "global","issuedCertPass" + s.getId());
			//uzimam sertifikat koji je potpisan od strane admin i izdat od strane admina za admina
			java.security.cert.Certificate certificateSelfSign = keyStoreReader.readCertificate("globalKeyStore", "global","selfAssignedCert");
			//uzimam javni kljuc 
			PublicKey publicKey = certificateSelfSign.getPublicKey();
			//proveravam da li je dobar digitalan potpis
			try {
				certificateSoft.verify(certificateSelfSign.getPublicKey());
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		return true;
	}
}
