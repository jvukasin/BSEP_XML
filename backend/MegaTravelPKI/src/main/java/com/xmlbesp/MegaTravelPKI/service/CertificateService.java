package com.xmlbesp.MegaTravelPKI.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import com.xmlbesp.MegaTravelPKI.dto.CertificateDTO;
import com.xmlbesp.MegaTravelPKI.dto.RevocationDTO;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xmlbesp.MegaTravelPKI.certificates.CertificateGenerator;
import com.xmlbesp.MegaTravelPKI.dto.CertificateInfoDTO;
import com.xmlbesp.MegaTravelPKI.dto.SubjectDataDTO;
import com.xmlbesp.MegaTravelPKI.keystores.KeyStoreReader;
import com.xmlbesp.MegaTravelPKI.keystores.KeyStoreWriter;
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
	private boolean loadExistingRootKeyStore = false;
	
	private KeyStoreReader keyStoreReader;
	private KeyStoreWriter keyStoreWriter;
	private KeyPair keyPairIssuer;

	@Value("${server.ssl.key-store-password}")
	private String keyStorePassword ;

	@Value("${server.ssl.key-alias}")
	private String rootCertificateAlias;

	@Value("${server.ssl.key-store}")
	private String rootKeyStorePath;

	@Value("${server.port}")
	private String port;

	private String validationApi;


	
	@PostConstruct
	public void init() throws ParseException {
		
		logger.logInfo("KS_INIT");
		validationApi = "https://localhost:" + port + "/certificate/validateCertificate/";
		
		keyStoreWriter = new KeyStoreWriter();
		keyStoreReader = new KeyStoreReader();
		
		logger.logInfo("KS_INIT_SUCCESS.");
		
	}



	public Certificate generateSelfSignedCertificate(CertificateInfoDTO cDTO) {

		logger.logInfo("SS_CERT_CREATE");
		// pravimo Certificate objekat sa informacijama koje za sada imamo, kada se upisemo, dobijamo njegov id koji ce biti
		// u ovom slucaju i issuerId jer je selfSigned, za sve ostalo issuerId ce biti onaj parent cert u lancu koji ga potpisuje
		Certificate cert = new Certificate();
		cert.setAlias(cDTO.getAlias());
		cert.setCa(true);
		cert.setStartDate(cDTO.getStartDate());
		cert.setEndDate(cDTO.getEndDate());
		cert.setRevoked(false);
		cert.setReasonForRevocation("");

		// cuvamo kako bi dobili ID
		cert = certRepo.save(cert);

		cert.setIdIssuer(cert.getId());
		cert.setValidationApi(validationApi + cert.getId());
		cert = certRepo.save(cert);

		this.keyPairIssuer = generateKeyPair();

		SubjectData subject = generateSubjectData(cert.getId(), cDTO);
		IssuerData issuer = generateIssuerData(keyPairIssuer.getPrivate(), cert.getId(), cDTO.getSubjectDataDTO());

		subject.setPublicKey(keyPairIssuer.getPublic());
		subject.setPrivateKey(keyPairIssuer.getPrivate());

		CertificateGenerator cg = new CertificateGenerator();

		try {
			X509Certificate certificate = cg.generateCertificate(subject, issuer);

			keyStoreWriter.loadKeyStore(null, this.keyStorePassword.toCharArray());

			keyStoreWriter.write(cDTO.getAlias(), keyPairIssuer.getPrivate(), this.keyStorePassword.toCharArray(),
					certificate);

			keyStoreWriter.saveKeyStore(formFileNameJKS(cDTO.getAlias()), keyStorePassword.toCharArray());

			logger.logInfo("SS_CERT_CREATE_SUCCESS");
			return cert;

		} catch (Exception e) {
			e.printStackTrace();

		}

		logger.logError("SS_CERT_CREATE_FAIL");
		return null;
	}
	
	private SubjectData generateSubjectData(Long certId, CertificateInfoDTO cDTO) {

		logger.logInfo("SD_ROOT_GEN");
		
		KeyPair keyPairSubject = generateKeyPair();
		String serial = certId.toString();
		logger.logInfo("SD_ROOT_GEN_SUCCESS");
	    return new SubjectData(keyPairSubject.getPublic(), keyPairSubject.getPrivate(),
				buildX500Data(certId, cDTO.getSubjectDataDTO()),
				serial, cDTO.getStartDate(), cDTO.getEndDate());
		
	}
	
	private IssuerData generateIssuerData(PrivateKey privateKey, Long certId, SubjectDataDTO sDTO) {

		logger.logInfo("ID_ROOT_GEN");

		logger.logInfo("ID_ROOT_GEN_SUCCESS");
	    return new IssuerData(privateKey, buildX500Data(certId, sDTO));
		
	}

	private X500Name buildX500Data(Long certId, SubjectDataDTO sDTO) {


		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);

		builder.addRDN(BCStyle.CN, sDTO.getCommonName());
		builder.addRDN(BCStyle.O, sDTO.getOrganisation());
		builder.addRDN(BCStyle.OU, sDTO.getOrganisationUnit());
		builder.addRDN(BCStyle.C, sDTO.getCountryCode());
		builder.addRDN(BCStyle.E, sDTO.getEmail());
		//UID certificate ID + timestamp
		builder.addRDN(BCStyle.UID, certId.toString() + "-" + System.currentTimeMillis());

		return builder.build();

	}
	
	public Certificate getSelfSigned() {

		logger.logInfo("SS_CERT_GET");
		List<Certificate> certifs = certRepo.findAll();
		
		for(Certificate c : certifs) {
			if (c.getId().equals(c.getIdIssuer())) {

				logger.logInfo("SS_CERT_GET_SUCCESS");
				return c;
			}
		}

		logger.logError("SS_CERT_GET_FAIL");
		return null;
	}
	

	public boolean selfSignedExists() {

		List<Certificate> certifs = certRepo.findAll();
		
		for(Certificate c : certifs) {
			if (c.getId().equals(c.getIdIssuer()) && !c.isRevoked()) {

				logger.logInfo("SS_CERT_EXISTS");
				return true;
			}
		}

		logger.logWarning("SS_CERT_NOT_EXISTS");
		return false;
	}
	
	// pravi se sertifikat sa informacijama iz subjectDataDTO i vezuje se na sertifikat koji ima idIssuer i to samo ukoliko je CA
	public Certificate generateIssuedCertificate(CertificateInfoDTO certInfoDTO) throws ParseException
	{
		logger.logInfo("IS_GEN");
		// ovde treba proveriti i validnost issuera
		Certificate parentCertificate = certRepo.findOneById(certInfoDTO.getIssuerId());
		
		if (parentCertificate == null || !parentCertificate.isCa() || parentCertificate.isRevoked()) {
			logger.logError("IS_GEN_FAIL");
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
		SubjectData subjectData = generateSubjectData(cert.getId(), certInfoDTO);
		
		// generisi sertifikat
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate certificate = cg.generateCertificate(subjectData, issuerData);
		
		keyStoreWriter.loadKeyStore(null, this.keyStorePassword.toCharArray());
		
		keyStoreWriter.write(certInfoDTO.getAlias(), subjectData.getPrivateKey(), keyStorePassword.toCharArray(), certificate);

        keyStoreWriter.saveKeyStore(formFileNameJKS(certInfoDTO.getAlias()), keyStorePassword.toCharArray());


		logger.logInfo("IS_GEN_SUCCESS");
		return cert;
	}
	
	public String validate(Certificate cert, Certificate parentCertificate) {
		// TODO: uraditi revoke za sve sertifikate u hijerarhiji

		logger.logInfo("CERT_VAL");
		
		Calendar thisMoment = Calendar.getInstance();
		thisMoment.set(Calendar.HOUR_OF_DAY, 0);
		thisMoment.set(Calendar.MINUTE, 0);
		thisMoment.set(Calendar.SECOND, 0);
		
		//napraviti Date objekat kako bi moglo da se poredi
		Date today = thisMoment.getTime();
		
		if(cert.isRevoked()) {
			logger.logWarning("CERT_VAL_REVOKED");
			return "The certificate has been revoked!";
		}else if(today.after(cert.getEndDate())) {
			logger.logWarning("CERT_VAL_EXPIRIED");
			return "The validation date has expired!";
		}else {
			// provera digitalnog potpisa
			// uzmi sertifikat za koji proveravas digitalni potpis
			java.security.cert.Certificate certificate =
					keyStoreReader.readCertificate(formFileNameJKS(cert.getAlias()), this.keyStorePassword,
							cert.getAlias());
			
			// nadji sertifikat issuer-a
			java.security.cert.Certificate issuerCertificate =
					keyStoreReader.readCertificate(formFileNameJKS(parentCertificate.getAlias()),
							this.keyStorePassword, parentCertificate.getAlias());

			try {
				certificate.verify(issuerCertificate.getPublicKey());
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				logger.logError("CERT_VAL_ERROR");
				return "The digital signature is not valid!";
			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				logger.logError("CERT_VAL_ERROR");
				return "The digital signature is not valid!";
				
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
				logger.logError("CERT_VAL_ERROR");
				return "The digital signature is not valid!";
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				logger.logError("CERT_VAL_ERROR");
				return "The digital signature is not valid!";
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				logger.logError("CERT_VAL_ERROR");
				
				return "The digital signature is not valid!";
			}
			
		}
		logger.logInfo("CERT_VAL_SUCCESS");
		return "The certificate is valid!";
	}
	
	private IssuerData getIssuerDataFromKeyStore(Certificate parentCertificate) {

		logger.logInfo("ID_GET_KS");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	    String name = formFileNameJKS(parentCertificate.getAlias());
	
	    KeyStoreReader keyStoreReader = new KeyStoreReader();
	    IssuerData issuerData = keyStoreReader.readIssuerFromStore(name,
	    														   parentCertificate.getAlias(),
	    														   this.keyStorePassword.toCharArray(),
	    														   this.keyStorePassword.toCharArray());
	

	    if (issuerData != null) {
	    	logger.logInfo("ID_GET_KS_SUCCESS");
	    } else {
	    	logger.logError("ID_GET_KS_FAIL");
	    }
	    
	    return issuerData;
		
	}
	

	
	
	
	//generise par kljuceva public i private
	//proveriti algoritme
	private KeyPair generateKeyPair() {

		logger.logInfo("KP_GEN");
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); 
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(2048, random);
			logger.logInfo("KP_GEN_SUCCESS");
			return keyGen.generateKeyPair();
			} catch (NoSuchAlgorithmException e) {

				logger.logError("KP_GEN_ERROR");
				e.printStackTrace();
			} catch (NoSuchProviderException e) {

				logger.logError("KP_GEN_ERROR");
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
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
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


	private String formFileNameJKS(String alias) {
		return alias.replace(" ", "") + ".jks";
	}

	private CertificateInfoDTO formSSGeneric() throws ParseException {

		CertificateInfoDTO cDTO = new CertificateInfoDTO();

		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date startDate = simpleDateFormat.parse("2019-04-11");
		Date endDate = simpleDateFormat.parse("2019-09-11");

		cDTO.setAlias(this.rootCertificateAlias);
		cDTO.setCa(true);
		cDTO.setStartDate(startDate);
		cDTO.setEndDate(endDate);

		SubjectDataDTO sDTO = new SubjectDataDTO();
		sDTO.setCommonName("localhost");
		sDTO.setOrganisation("MT");
		sDTO.setOrganisationUnit("MegaTravel");
		sDTO.setCountryCode("RS");
		sDTO.setEmail("megatravel@gmail.com");

		cDTO.setSubjectDataDTO(sDTO);

		return cDTO;
	}

	public List<CertificateDTO> revoke(RevocationDTO revocationDTO) {

		Certificate c = certRepo.findOneById(revocationDTO.getIssuerId());
		c.setRevoked(true);
		c.setReasonForRevocation(revocationDTO.getReasonForRevocation());

		List<CertificateDTO> retVal = new ArrayList<>();

		for (Certificate cert: certRepo.findAll()) {
			if (cert.getIdIssuer().equals(c.getId())) {
				cert.setRevoked(true);
				cert.setReasonForRevocation("Cascade revocation: " + revocationDTO.getReasonForRevocation());
				cert = certRepo.save(cert);
			}

			retVal.add(new CertificateDTO(cert));


		}
		return retVal;


	}

	public void formTrustStores(Certificate certificateA, Certificate certificateB) throws KeyStoreException {


		keyStoreWriter.loadKeyStore(formFileNameJKS(certificateA.getAlias()), this.keyStorePassword.toCharArray());
		java.security.cert.Certificate certB = keyStoreReader.readCertificate(formFileNameJKS(certificateA.getAlias()),
				this.keyStorePassword,
				certificateA.getAlias());
		keyStoreWriter.trustedEntry(certificateB.getAlias(), certB);
		keyStoreWriter.saveKeyStore(formFileNameJKS(certificateA.getAlias()), this.keyStorePassword.toCharArray());



		keyStoreWriter.loadKeyStore(formFileNameJKS(certificateB.getAlias()), this.keyStorePassword.toCharArray());
		java.security.cert.Certificate certA = keyStoreReader.readCertificate(formFileNameJKS(certificateA.getAlias()),
				this.keyStorePassword,
				certificateA.getAlias());
		keyStoreWriter.trustedEntry(certificateA.getAlias(), certA);
		keyStoreWriter.saveKeyStore(formFileNameJKS(certificateB.getAlias()), this.keyStorePassword.toCharArray());



	}
}
