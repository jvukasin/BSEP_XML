package com.xmlbesp.MegaTravelPKI.dto;

import java.util.Date;

public class CertificateInfoDTO {
	
	private Long issuerId;
	private boolean ca;
	private String alias;
	private Date startDate;
	private Date endDate;
	private SubjectDataDTO subjectDataDTO;
	
	public CertificateInfoDTO() {
		
	}
	
	public Long getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(Long issuerId) {
		this.issuerId = issuerId;
	}
	public boolean isCa() {
		return ca;
	}
	public void setCa(boolean ca) {
		this.ca = ca;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public SubjectDataDTO getSubjectDataDTO() {
		return subjectDataDTO;
	}
	public void setSubjectDataDTO(SubjectDataDTO subjectDataDTO) {
		this.subjectDataDTO = subjectDataDTO;
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
	
	
	
}
