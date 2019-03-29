package com.xmlbesp.MegaTravelPKI.dto;

public class CommunicationDTO {
	
	private Long softwareAId;
	private Long softwareBId;
	
	public CommunicationDTO() {
		
	}
	
	public CommunicationDTO(Long softwareAId, Long softwareBId) {
		super();
		this.softwareAId = softwareAId;
		this.softwareBId = softwareBId;
	}

	public Long getSoftwareAId() {
		return softwareAId;
	}

	public void setSoftwareAId(Long softwareAId) {
		this.softwareAId = softwareAId;
	}

	public Long getSoftwareBId() {
		return softwareBId;
	}

	public void setSoftwareBId(Long softwareBId) {
		this.softwareBId = softwareBId;
	}
	
	

}
