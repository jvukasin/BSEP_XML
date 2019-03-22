package com.xmlbesp.MegaTravelPKI.controller;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xmlbesp.MegaTravelPKI.certificates.CertificateGenerator;
import com.xmlbesp.MegaTravelPKI.dto.CertificateDTO;
import com.xmlbesp.MegaTravelPKI.dto.SubjectDataDTO;
import com.xmlbesp.MegaTravelPKI.keystores.KeyStoreWriter;
import com.xmlbesp.MegaTravelPKI.model.AdminPKI;
import com.xmlbesp.MegaTravelPKI.model.Certificate;
import com.xmlbesp.MegaTravelPKI.model.IssuerData;
import com.xmlbesp.MegaTravelPKI.model.SubjectData;
import com.xmlbesp.MegaTravelPKI.model.User;
import com.xmlbesp.MegaTravelPKI.service.AdminPKIService;
import com.xmlbesp.MegaTravelPKI.service.CertificateService;


@RestController
@RequestMapping(value="/certificate")
public class CertificateController {
	
	@Autowired
	CertificateService certificateService;
	
	@Autowired
	AdminPKIService adminPKIService;
	
	private KeyStoreWriter keyStoreWriter;
	
	@PostConstruct
	public void init() {
		keyStoreWriter = new KeyStoreWriter();
		String global = "global";
		keyStoreWriter.loadKeyStore(null, global.toCharArray());
		keyStoreWriter.saveKeyStore("globalKeyStore",global.toCharArray());
	}
	
	@RequestMapping(value = "/selfSigned", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean selfSignedExists() {
		
		List<Certificate> certifs = certificateService.findAll();
		for(Certificate c : certifs) {
			if(c.isCa()) return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/generateSelfSigned",method = RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<CertificateDTO> generateSelfSigned(@RequestBody SubjectDataDTO subjectDataDTO) throws ParseException
	{
		
		List<AdminPKI> admins = adminPKIService.findAll();
		//postoji za sada samo jedan admin
		AdminPKI admin = admins.get(0);
		
		Certificate cert = new Certificate(admin.getId(),admin.getId(),subjectDataDTO.getStartDate(), subjectDataDTO.getEndDate(), false,true,"");
		cert = certificateService.save(cert);
		
		SubjectData subject = generateSubjectData(cert.getId(),admin,subjectDataDTO.getStartDate(),subjectDataDTO.getEndDate());
		
		KeyPair keyPairIssuer = generateKeyPair();
		IssuerData issuer = generateIssuerData(keyPairIssuer.getPrivate(),admin);
		
		//ovde vraca javin Certificate objekat, ne nas iz modela
		java.security.cert.Certificate certificate = createCertificate(subject,issuer,keyPairIssuer.getPublic());
		String pass = "selfSignPass";
		keyStoreWriter.write("selfSignCertificate", keyPairIssuer.getPrivate(), pass.toCharArray(), certificate);
		
		return new ResponseEntity<>(new CertificateDTO(cert), HttpStatus.OK);
	}
	
	private SubjectData generateSubjectData(Long certId, Object subject, Date startDate, Date endDate) {
		
		KeyPair keyPairSubject = generateKeyPair();
		String serial = certId.toString();
		
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		if(subject instanceof AdminPKI) {
			AdminPKI admin = (AdminPKI) subject;
			
		    builder.addRDN(BCStyle.SURNAME, admin.getLastName());
		    builder.addRDN(BCStyle.GIVENNAME, admin.getName());
		    builder.addRDN(BCStyle.E, admin.getEmail());
		    //UID (USER ID) je ID korisnika
		    builder.addRDN(BCStyle.UID, admin.getId().toString());
			
		}else if(subject instanceof User) {
			
		}else {
			
		}
		
		//Kreiraju se podaci za sertifikat, sto ukljucuje:
	    // - javni kljuc koji se vezuje za sertifikat
	    // - podatke o vlasniku
	    // - serijski broj sertifikata
	    // - od kada do kada vazi sertifikat
	    return new SubjectData(keyPairSubject.getPublic(), builder.build(), serial, startDate, endDate);
	}
	
	private IssuerData generateIssuerData(PrivateKey privateKey, AdminPKI admin) {
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
	    
	    builder.addRDN(BCStyle.GIVENNAME, admin.getName());
	    builder.addRDN(BCStyle.SURNAME, admin.getLastName());
	    builder.addRDN(BCStyle.E, admin.getEmail());
	    //UID (USER ID) je ID korisnika
	    builder.addRDN(BCStyle.UID, admin.getId().toString());
	    
	    //Kreiraju se podaci za issuer-a, sto u ovom slucaju ukljucuje:
	    // - privatni kljuc koji ce se koristiti da potpise sertifikat koji se izdaje
	    // - podatke o vlasniku sertifikata koji izdaje nov sertifikat
	    return new IssuerData(privateKey, builder.build());
	}
	
	//generise par kljuceva public i private
	//proveriti algoritme
	private KeyPair generateKeyPair() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); 
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(2048, random);
			return keyGen.generateKeyPair();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				e.printStackTrace();
			}
	        return null;
	}	
	
	private java.security.cert.Certificate createCertificate(SubjectData subjectData, IssuerData issuerData, PublicKey publicKey) {
		//Generise se sertifikat za subjekta, potpisan od strane issuer-a
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate cert = cg.generateCertificate(subjectData, issuerData);
		try {
			//Moguce je proveriti da li je digitalan potpis sertifikata ispravan, upotrebom javnog kljuca izdavaoca
			//ovde vraca javin Certificate objekat, ne nas iz modela
			cert.verify(publicKey);
			return cert;
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
