package com.xmlbesp.MegaTravelPKI.service;

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
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.xmlbesp.MegaTravelPKI.certificates.CertificateGenerator;
import com.xmlbesp.MegaTravelPKI.dto.SoftwareDTO;
import com.xmlbesp.MegaTravelPKI.dto.SubjectDataDTO;
import com.xmlbesp.MegaTravelPKI.keystores.KeyStoreWriter;
import com.xmlbesp.MegaTravelPKI.model.AdminPKI;
import com.xmlbesp.MegaTravelPKI.model.Certificate;
import com.xmlbesp.MegaTravelPKI.model.IssuerData;
import com.xmlbesp.MegaTravelPKI.model.Software;
import com.xmlbesp.MegaTravelPKI.model.SubjectData;
import com.xmlbesp.MegaTravelPKI.model.User;
import com.xmlbesp.MegaTravelPKI.repository.CertificateRepository;

@Service
public class CertificateService {
	
	@Autowired
	CertificateRepository certRepo;
	
	private boolean loadExistingRootKeyStore = true;
	
	private KeyStoreWriter keyStoreWriter;
	private KeyPair keyPairIssuer;
	
	private String rootKeyStoreName = "rootKeyStore.p12";
	private String rootKeyStorePassword = "mnogodobrasifra";
	
	
	
	@PostConstruct
	public void init() throws ParseException {
		
		keyStoreWriter = new KeyStoreWriter();
		
		if (loadExistingRootKeyStore) {
			keyStoreWriter.loadKeyStore(rootKeyStoreName, rootKeyStorePassword.toCharArray());
		} else {
			keyStoreWriter.loadKeyStore(null, rootKeyStorePassword.toCharArray());
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Date startDate = simpleDateFormat.parse("2018-04-11");
			Date endDate = simpleDateFormat.parse("2018-09-11");
			  
			generateSelfSignedCertificate(startDate, endDate);
			  
			keyStoreWriter.saveKeyStore(rootKeyStoreName, rootKeyStorePassword.toCharArray());
		}
	}
	
	public Certificate generateSelfSignedCertificate(Date startDate, Date endDate) {
		
		Certificate cert = new Certificate(new Long(0) , new Long(0), startDate, endDate, false, true, "");
		cert = certRepo.save(cert);

		this.keyPairIssuer = generateKeyPair();
		 
		SubjectData subject = generateRootSubjectData(cert.getId(), startDate, endDate);
		IssuerData issuer = generateRootIssuerData(keyPairIssuer.getPrivate());
		
		subject.setPublicKey(keyPairIssuer.getPublic());
		subject.setPrivateKey(keyPairIssuer.getPrivate());
		
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate certificate = cg.generateCertificate(subject, issuer);
		
		String pass = "mnogodobrasifra";
		keyStoreWriter.write("bbf", keyPairIssuer.getPrivate(), pass.toCharArray(), certificate);
		
		return cert;
	}
	
	private SubjectData generateRootSubjectData(Long certId, Date startDate, Date endDate) {
		
		KeyPair keyPairSubject = generateKeyPair();
		String serial = certId.toString();
		
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		
		builder.addRDN(BCStyle.CN, "BBF Root CA");
	    builder.addRDN(BCStyle.O, "BBF");
	    builder.addRDN(BCStyle.OU, "BBF Ltd");
	    builder.addRDN(BCStyle.C, "RS");
	    builder.addRDN(BCStyle.E, "bbf_for_ever@gmail.com");
	    //UID (USER ID) je ID korisnika
	    builder.addRDN(BCStyle.UID, "123456");
	    
	    return new SubjectData(keyPairSubject.getPublic(), keyPairSubject.getPrivate(), builder.build(), serial, startDate, endDate);
		
	}
	
	private IssuerData generateRootIssuerData(PrivateKey privateKey) {
		
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
    
		builder.addRDN(BCStyle.CN, "BBF Root CA");
	    builder.addRDN(BCStyle.O, "BBF");
	    builder.addRDN(BCStyle.OU, "BBF Ltd");
	    builder.addRDN(BCStyle.C, "RS");
	    builder.addRDN(BCStyle.E, "bbf_for_ever@gmail.com");
	    //UID (USER ID) je ID korisnika
	    builder.addRDN(BCStyle.UID, "123456");
    
  
	    return new IssuerData(privateKey, builder.build());
		
	}
	
	public Certificate getSelfSigned() {
		List<Certificate> certifs = certRepo.findAll();
		
		for(Certificate c : certifs) {
			if (c.getIdSubject().equals(c.getIdIssuer())) {
				return c;
			}
		}
		
		return null;
	}
	

	public boolean selfSignedExists() {
		List<Certificate> certifs = certRepo.findAll();
		
		for(Certificate c : certifs) {
			if (c.getIdSubject().equals(c.getIdIssuer())) {
				return true;
			}
		}
		
		return false;
	}
	
	// pravi se sertifikat sa informacijama iz subjectDataDTO i vezuje se na sertifikat koji ima idIssuer i to samo ukoliko je CA
	public Certificate generateIssuedCertificate(Long idIssuer, SubjectDataDTO subjectDataDTO) throws ParseException
	{
		
		Certificate cert = new Certificate(idIssuer, new Long(0), subjectDataDTO.getStartDate(), subjectDataDTO.getEndDate(), false,true,"");
		cert = certRepo.save(cert);
		
//		List<AdminPKI> admins = adminPKIService.findAll();
//		//postoji za sada samo jedan admin
//		AdminPKI admin = admins.get(0);
//		
//		Certificate cert = new Certificate(admin.getId(),idSubject,subjectDataDTO.getStartDate(), subjectDataDTO.getEndDate(), false,true,"");
//		cert = certificateService.save(cert);
//		
//		Software soft = softwareService.findOneById(idSubject);
//		
//		SubjectData subjectD = generateSubjectData(cert.getId(),soft,subjectDataDTO.getStartDate(),subjectDataDTO.getEndDate());
//		IssuerData issuerD = generateIssuerData(keyPairIssuer.getPrivate(), admin);
//		
//		CertificateGenerator cg = new CertificateGenerator();
//		X509Certificate certificate = cg.generateCertificate(subjectD, issuerD);
//		
//		//ovde vraca javin Certificate objekat, ne nas iz modela
////		java.security.cert.Certificate certificate = createCertificate(subjectD, issuerD, keyPairIssuer.getPublic());
//		String pass = "issuedCertPass" + soft.getId();
//		keyStoreWriter.write(pass, subjectD.getPrivateKey() , pass.toCharArray(), certificate);
//		keyStoreWriter.saveKeyStore("globalKeyStore", "global".toCharArray());
//		
//		KeyStoreWriter keyStoreWriterModule = new KeyStoreWriter();
//		String alias = soft.getName() + soft.getId();
//		keyStoreWriterModule.loadKeyStore(null, alias.toCharArray());
//		keyStoreWriterModule.saveKeyStore(alias + "KeyStore", alias.toCharArray());
//		String localAlias="myCertificate";
//		
//		keyStoreWriterModule.write(localAlias, subjectD.getPrivateKey(), localAlias.toCharArray(), certificate);
//		
//		soft.setCertified(true);
//		soft.setCertificate(cert);
//		soft = softwareService.save(soft);
		
		return cert;
	}
	
	
	
	// PRIVATE METHODS 
	
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

}
