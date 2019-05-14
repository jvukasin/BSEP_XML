package com.xmlbesp.MegaTravelPKI.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
import org.springframework.stereotype.Service;

import com.xmlbesp.MegaTravelPKI.certificates.CertificateGenerator;
import com.xmlbesp.MegaTravelPKI.dto.CertificateInfoDTO;
import com.xmlbesp.MegaTravelPKI.dto.SubjectDataDTO;
import com.xmlbesp.MegaTravelPKI.keystores.KeyStoreReader;
import com.xmlbesp.MegaTravelPKI.keystores.KeyStoreWriter;
import com.xmlbesp.MegaTravelPKI.model.AdminPKI;
import com.xmlbesp.MegaTravelPKI.model.Certificate;
import com.xmlbesp.MegaTravelPKI.model.IssuerData;
import com.xmlbesp.MegaTravelPKI.model.SubjectData;
import com.xmlbesp.MegaTravelPKI.repository.CertificateRepository;

@Service
public class CertificateService {
	
	@Autowired
	CertificateRepository certRepo;
	
	private Logging logger = new Logging(this);
	
	// da li se loaduje postojeci key store ili se pravi novi
	private boolean loadExistingRootKeyStore = true;
	
	private KeyStoreReader keyStoreReader;
	private KeyStoreWriter keyStoreWriter;
	private KeyPair keyPairIssuer;
	private String validationApi = "https://localhost:8443/certificate/validateCertificate/";
	
	private String keyStorePassword = "mnogodobrasifra";
	private String rootCertificateAlias = "bbf";
	
	@PostConstruct
	public void init() throws ParseException {
		
		keyStoreWriter = new KeyStoreWriter();
		keyStoreReader = new KeyStoreReader();
		
		if (loadExistingRootKeyStore) {
			keyStoreWriter.loadKeyStore(formFileName(rootCertificateAlias), keyStorePassword.toCharArray());
			
			// PRIVREMENO - dok je hibernate postavljen na create-drop
			if (!selfSignedExists()) {
				
			}
			
		} else {
			keyStoreWriter.loadKeyStore(null, keyStorePassword.toCharArray());
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Date startDate = simpleDateFormat.parse("2019-04-11");
			Date endDate = simpleDateFormat.parse("2019-09-11");
			  
			generateSelfSignedCertificate(startDate, endDate);
			  
			keyStoreWriter.saveKeyStore(formFileName(rootCertificateAlias), keyStorePassword.toCharArray());
		}
	}
	
	public Certificate generateSelfSignedCertificate(Date startDate, Date endDate) {
		
		logger.logInfo("GENSELFSIGNED - START");
		// pravimo Certificate objekat sa informacijama koje za sada imamo, kada se upisemo, dobijamo njegov id koji ce biti
		// u ovom slucaju i issuerId jer je selfSigned, za sve ostalo issuerId ce biti onaj parent cert u lancu koji ga potpisuje
		Certificate cert = new Certificate();
		cert.setAlias(this.rootCertificateAlias);
		cert.setCa(true);
		cert.setStartDate(startDate);
		cert.setEndDate(endDate);
		cert.setRevoked(false);
		cert.setReasonForRevocation("");
		
		// cuvamo kako bi dobili ID
		cert = certRepo.save(cert);

		cert.setIdIssuer(cert.getId());
		cert.setValidationApi(validationApi + cert.getId());
		cert = certRepo.save(cert);
		
		this.keyPairIssuer = generateKeyPair();
		 
		SubjectData subject = generateRootSubjectData(cert.getId(), startDate, endDate);
		IssuerData issuer = generateRootIssuerData(keyPairIssuer.getPrivate());
		
		subject.setPublicKey(keyPairIssuer.getPublic());
		subject.setPrivateKey(keyPairIssuer.getPrivate());
		
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate certificate = cg.generateCertificate(subject, issuer);
		
		keyStoreWriter.write(this.rootCertificateAlias, keyPairIssuer.getPrivate(), this.keyStorePassword.toCharArray(), certificate);
		
		logger.logInfo("GENSELFSIGNED - END");
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
			if (c.getId().equals(c.getIdIssuer())) {
				return c;
			}
		}
		
		return null;
	}
	

	public boolean selfSignedExists() {
		List<Certificate> certifs = certRepo.findAll();
		
		for(Certificate c : certifs) {
			if (c.getId().equals(c.getIdIssuer())) {
				return true;
			}
		}
		
		return false;
	}
	
	// pravi se sertifikat sa informacijama iz subjectDataDTO i vezuje se na sertifikat koji ima idIssuer i to samo ukoliko je CA
	public Certificate generateIssuedCertificate(CertificateInfoDTO certInfoDTO) throws ParseException
	{
		logger.logInfo("GENCERT - START");
		// ovde treba proveriti i validnost issuera
		Certificate parentCertificate = certRepo.getOne(certInfoDTO.getIssuerId());
		
		if (parentCertificate == null || !parentCertificate.isCa()) {
			logger.logError("Cannot create certificate;no parentCert/no CA");
			return null;
		}
		
		Certificate cert = new Certificate();
		cert.setIdIssuer(certInfoDTO.getIssuerId());
		cert.setAlias(certInfoDTO.getAlias());
		cert.setStartDate(certInfoDTO.getStartDate());
		cert.setEndDate(certInfoDTO.getEndDate());
		cert.setCa(certInfoDTO.isCa());
		cert.setRevoked(false);
		cert.setReasonForRevocation("");
		
		
		
		cert = certRepo.save(cert);
		
		cert.setValidationApi(validationApi + cert.getId());
		
		cert = certRepo.save(cert);
		
		// dobavi objekat IssuerData iz keystorea u kom se nalazi taj issuer
		IssuerData issuerData = getIssuerDataFromKeyStore(parentCertificate);
		SubjectData subjectData = generateSubjectData(cert.getId(), certInfoDTO.getSubjectDataDTO(),
												certInfoDTO.getStartDate(), certInfoDTO.getEndDate());
		
		// generisi sertifikat
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate certificate = cg.generateCertificate(subjectData, issuerData);
		
		keyStoreWriter.loadKeyStore(null, this.keyStorePassword.toCharArray());
		
		keyStoreWriter.write(certInfoDTO.getAlias(), subjectData.getPrivateKey(), keyStorePassword.toCharArray(), certificate);

        keyStoreWriter.saveKeyStore(formFileName(certInfoDTO.getAlias()), keyStorePassword.toCharArray());
		
		
//		
//		Software soft = softwareService.findOneById(idSubject);
//		
//		soft.setCertified(true);
//		soft.setCertificate(cert);
//		soft = softwareService.save(soft);
		
        logger.logInfo("GENCERT - END");
		return cert;
	}
	
	public String validate(Certificate cert, Certificate parentCertificate) {
		// TODO: uraditi revoke za sve sertifikate u hijerarhiji
		
		Calendar thisMoment = Calendar.getInstance();
		thisMoment.set(Calendar.HOUR_OF_DAY, 0);
		thisMoment.set(Calendar.MINUTE, 0);
		thisMoment.set(Calendar.SECOND, 0);
		
		//napraviti Date objekat kako bi moglo da se poredi
		Date today = thisMoment.getTime();
		
		if(cert.isRevoked()) {
			return "The certificate has been revoked!";
		}else if(today.after(cert.getEndDate())) {
			return "The validation date has expired!";
		}else {
			// provera digitalnog potpisa
			// uzmi sertifikat za koji proveravas digitalni potpis
			java.security.cert.Certificate certificate = keyStoreReader.readCertificate(formFileName(cert.getAlias()), this.keyStorePassword, cert.getAlias());
			
			// nadji sertifikat issuer-a
			java.security.cert.Certificate issuerCertificate = keyStoreReader.readCertificate(formFileName(parentCertificate.getAlias()), this.keyStorePassword, parentCertificate.getAlias());
			
			try {
				certificate.verify(issuerCertificate.getPublicKey());
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "The digital signature is not valid!";
			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "The digital signature is not valid!";
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "The digital signature is not valid!";
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "The digital signature is not valid!";
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "The digital signature is not valid!";
			}
			
		}
		return "The certificate is valid!";
	}
	
	private IssuerData getIssuerDataFromKeyStore(Certificate parentCertificate) {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	    String name = formFileName(parentCertificate.getAlias());
	
	    KeyStoreReader keyStoreReader = new KeyStoreReader();
	    IssuerData issuerData = keyStoreReader.readIssuerFromStore(name,
	    														   parentCertificate.getAlias(),
	    														   this.keyStorePassword.toCharArray(),
	    														   this.keyStorePassword.toCharArray());
	
	    return issuerData;
		
	}
	
	
	// PRIVATE METHODS 
	
	private SubjectData generateSubjectData(Long certId, SubjectDataDTO subjectDataDTO, Date startDate, Date endDate) {
		
		KeyPair keyPairSubject = generateKeyPair();
		String serial = certId.toString();
		
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);

		builder.addRDN(BCStyle.CN, subjectDataDTO.getCompanyName());
	    builder.addRDN(BCStyle.O, subjectDataDTO.getOrganisation());
	    builder.addRDN(BCStyle.OU, subjectDataDTO.getOrganisationUnit());
	    builder.addRDN(BCStyle.C, subjectDataDTO.getCountryCode());
	    builder.addRDN(BCStyle.E, subjectDataDTO.getEmail());
	    //UID (USER ID) je ID korisnika
	    builder.addRDN(BCStyle.UID, subjectDataDTO.getUid());
		
		/*
		 * if(subject instanceof AdminPKI) { AdminPKI admin = (AdminPKI) subject;
		 * 
		 * builder.addRDN(BCStyle.SURNAME, admin.getLastName());
		 * builder.addRDN(BCStyle.GIVENNAME, admin.getName()); builder.addRDN(BCStyle.E,
		 * admin.getEmail()); //UID (USER ID) je ID korisnika
		 * builder.addRDN(BCStyle.UID, admin.getId().toString());
		 * 
		 * }else if(subject instanceof User) {
		 * 
		 * }else { Software soft = (Software) subject; builder.addRDN(BCStyle.GIVENNAME,
		 * soft.getName()); builder.addRDN(BCStyle.UID, soft.getId().toString()); }
		 */
		
		//Kreiraju se podaci za sertifikat, sto ukljucuje:
	    // - javni kljuc koji se vezuje za sertifikat
	    // - podatke o vlasniku
	    // - serijski broj sertifikata
	    // - od kada do kada vazi sertifikat
	    return new SubjectData(keyPairSubject.getPublic(), keyPairSubject.getPrivate(), builder.build(), serial, 
	    															startDate, endDate);
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
	
	
	// ova metoda je prakticno nepotrebna
	private X509Certificate createCertificate(SubjectData subjectData, IssuerData issuerData, PublicKey publicKey) {
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
	
	public Certificate findOneById(Long id) {
		return certRepo.findOneById(id);
	}
	
	public List<Certificate> findAll() {
		return certRepo.findAll();
	}
	
	
	private String formFileName(String alias) {
		return "ks_" + alias.replace(" ", "") + ".p12";
	}

}
