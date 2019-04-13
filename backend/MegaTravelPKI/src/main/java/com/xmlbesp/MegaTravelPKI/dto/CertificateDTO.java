package com.xmlbesp.MegaTravelPKI.dto;

import java.util.Date;

import com.xmlbesp.MegaTravelPKI.model.Certificate;

public class CertificateDTO {

	private Long id;
	private Long idIssuer;
	private String alias;
	private Date startDate;
	private Date endDate;
	private boolean revoked;
	private boolean ca;
	private String reasonForRevocation;
	
	public CertificateDTO() {
		
	}
	
	public CertificateDTO(Certificate c) {
		this(c.getId(), c.getIdIssuer(), c.getAlias(), c.getStartDate(), c.getEndDate(), c.isCa(), c.isRevoked(), c.getReasonForRevocation());
	}
	
	
	
	public CertificateDTO(Long id, Long idIssuer, String alias, Date startDate, Date endDate, boolean revoked,
			boolean ca, String reasonForRevocation) {
		super();
		this.id = id;
		this.idIssuer = idIssuer;
		this.alias = alias;
		this.startDate = startDate;
		this.endDate = endDate;
		this.ca = ca;
		this.revoked = revoked;
		this.reasonForRevocation = reasonForRevocation;
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
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
