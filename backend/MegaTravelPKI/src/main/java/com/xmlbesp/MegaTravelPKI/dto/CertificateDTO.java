package com.xmlbesp.MegaTravelPKI.dto;

import java.util.Date;

import com.xmlbesp.MegaTravelPKI.model.Certificate;

public class CertificateDTO {

	private Long id;
	private Long idIssuer;
	private Long idSubject;
	private Date startDate;
	private Date endDate;
	private boolean revoked;
	private boolean ca;
	private String reasonForRevocation;
	
	public CertificateDTO() {
		
	}
	
	public CertificateDTO(Certificate c) {
		this(c.getId(), c.getIdIssuer(), c.getIdSubject(), c.getStartDate(), c.getEndDate(), c.isRevoked(), c.isCa(), c.getReasonForRevocation());
	}
	
	public CertificateDTO(Long id, Long idIssuer, Long idSubject, Date startDate, Date endDate, boolean revoked, boolean ca, String reason) {
		this.id = id;
		this.idIssuer = idIssuer;
		this.idSubject = idSubject;
		this.startDate = startDate;
		this.endDate = endDate;
		this.revoked = revoked;
		this.ca = ca;
		this.reasonForRevocation = reason;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdIssuer() {
		return idIssuer;
	}

	public void setIdIssuer(Long idIssuer) {
		this.idIssuer = idIssuer;
	}

	public Long getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(Long idSubject) {
		this.idSubject = idSubject;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isRevoked() {
		return revoked;
	}

	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}

	public boolean isCa() {
		return ca;
	}

	public void setCa(boolean ca) {
		this.ca = ca;
	}

	public String getReasonForRevocation() {
		return reasonForRevocation;
	}

	public void setReasonForRevocation(String reasonForRevocation) {
		this.reasonForRevocation = reasonForRevocation;
	}
	
	
}
