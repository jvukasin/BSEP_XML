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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.xmlbesp.MegaTravelPKI.dto.RevocationDTO;
import com.xmlbesp.MegaTravelPKI.dto.SoftwareDTO;
import com.xmlbesp.MegaTravelPKI.dto.SubjectDataDTO;
import com.xmlbesp.MegaTravelPKI.keystores.KeyStoreReader;
import com.xmlbesp.MegaTravelPKI.keystores.KeyStoreWriter;
import com.xmlbesp.MegaTravelPKI.model.AdminPKI;
import com.xmlbesp.MegaTravelPKI.model.Certificate;
import com.xmlbesp.MegaTravelPKI.model.IssuerData;
import com.xmlbesp.MegaTravelPKI.model.Software;
import com.xmlbesp.MegaTravelPKI.model.SubjectData;
import com.xmlbesp.MegaTravelPKI.model.User;
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
	
	private KeyStoreWriter keyStoreWriter;
	private KeyPair keyPairIssuer;
	
	@PostConstruct
	public void init() throws ParseException {
		String rootKeyStoreName = "rootKeyStore.p12";
		String rootKeyStorePassword = "mnogodobrasifra";
		
		keyStoreWriter = new KeyStoreWriter();
		keyStoreWriter.loadKeyStore(null, rootKeyStoreName.toCharArray());
		keyStoreWriter.saveKeyStore("rootKeyStore", rootKeyStoreName.toCharArray());
		keyPairIssuer = generateKeyPair();
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		Date startDate = simpleDateFormat.parse("2018-04-11");
		Date endDate = simpleDateFormat.parse("2018-09-11");
		
		generateSelfSigned(startDate, endDate);

		keyStoreWriter.saveKeyStore(rootKeyStoreName, rootKeyStorePassword.toCharArray());

	}
	
	@RequestMapping(value = "/selfSigned", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean selfSignedExists() {
		
		List<Certificate> certifs = certificateService.findAll();
	
		for(Certificate c : certifs) {
			if (c.getIdSubject().equals(c.getIdIssuer())) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public void generateSelfSigned(Date startDate, Date endDate){  
		Certificate cert = new Certificate(new Long(0) , new Long(0), startDate, endDate, false, true, "");
		cert = certificateService.save(cert);
		
		SubjectData subject = generateRootSubjectData(cert.getId(), startDate, endDate);
		IssuerData issuer = generateRootIssuerData(keyPairIssuer.getPrivate());
		
		subject.setPublicKey(keyPairIssuer.getPublic());
		subject.setPrivateKey(keyPairIssuer.getPrivate());
		
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate certificate = cg.generateCertificate(subject, issuer);
		
		String pass = "rootCAPass";
		keyStoreWriter.write("selfAssignedCert", keyPairIssuer.getPrivate(), pass.toCharArray(), certificate);
	}
	
	
	@RequestMapping(value = "/generateCertificate/{idSub}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<SoftwareDTO> generateCertificate(@PathVariable("idSub") Long idSubject, @RequestBody SubjectDataDTO subjectDataDTO) throws ParseException
	{
		List<AdminPKI> admins = adminPKIService.findAll();
		//postoji za sada samo jedan admin
		AdminPKI admin = admins.get(0);
		
		Certificate cert = new Certificate(admin.getId(),idSubject,subjectDataDTO.getStartDate(), subjectDataDTO.getEndDate(), false,true,"");
		cert = certificateService.save(cert);
		
		Software soft = softwareService.findOneById(idSubject);
		
		SubjectData subjectD = generateSubjectData(cert.getId(),soft,subjectDataDTO.getStartDate(),subjectDataDTO.getEndDate());
		IssuerData issuerD = generateIssuerData(keyPairIssuer.getPrivate(), admin);
		
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate certificate = cg.generateCertificate(subjectD, issuerD);
		
		//ovde vraca javin Certificate objekat, ne nas iz modela
//		java.security.cert.Certificate certificate = createCertificate(subjectD, issuerD, keyPairIssuer.getPublic());
		String pass = "issuedCertPass" + soft.getId();
		keyStoreWriter.write(pass, subjectD.getPrivateKey() , pass.toCharArray(), certificate);
		keyStoreWriter.saveKeyStore("globalKeyStore", "global".toCharArray());
		
		KeyStoreWriter keyStoreWriterModule = new KeyStoreWriter();
		String alias = soft.getName() + soft.getId();
		keyStoreWriterModule.loadKeyStore(null, alias.toCharArray());
		keyStoreWriterModule.saveKeyStore(alias + "KeyStore", alias.toCharArray());
		String localAlias="myCertificate";
		
		keyStoreWriterModule.write(localAlias, subjectD.getPrivateKey(), localAlias.toCharArray(), certificate);
		
		soft.setCertified(true);
		soft.setCertificate(cert);
		soft = softwareService.save(soft);
		
		return new ResponseEntity<>(new SoftwareDTO(soft), HttpStatus.OK);
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
	
	private SubjectData generateRootSubjectData(Long certId, Date startDate, Date endDate) {
		
		KeyPair keyPairSubject = generateKeyPair();
		String serial = certId.toString();
		
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		
	    builder.addRDN(BCStyle.GIVENNAME, "BBF Root CA");
	    builder.addRDN(BCStyle.E, "we_are_bbf@gmail.com");
	    builder.addRDN(BCStyle.UID, "vladajovelazyka");
		
	    return new SubjectData(keyPairSubject.getPublic(), keyPairSubject.getPrivate(), builder.build(), serial, startDate, endDate);
		
	}
	
	private IssuerData generateRootIssuerData(PrivateKey privateKey) {
		
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
    
		builder.addRDN(BCStyle.GIVENNAME, "BBF Root CA");
	    builder.addRDN(BCStyle.E, "we_are_bbf@gmail.com");
	    builder.addRDN(BCStyle.UID, "vladajovelazyka");
    
  
	    return new IssuerData(privateKey, builder.build());
		
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
			Software soft = (Software) subject;
			builder.addRDN(BCStyle.GIVENNAME, soft.getName());
			builder.addRDN(BCStyle.UID, soft.getId().toString());
		}
		
		//Kreiraju se podaci za sertifikat, sto ukljucuje:
	    // - javni kljuc koji se vezuje za sertifikat
	    // - podatke o vlasniku
	    // - serijski broj sertifikata
	    // - od kada do kada vazi sertifikat
	    return new SubjectData(keyPairSubject.getPublic(), keyPairSubject.getPrivate(), builder.build(), serial, startDate, endDate);
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
