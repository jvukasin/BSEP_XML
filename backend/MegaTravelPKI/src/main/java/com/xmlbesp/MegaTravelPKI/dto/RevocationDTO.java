package com.xmlbesp.MegaTravelPKI.dto;

public class RevocationDTO {
	
	private Long issuerId;
	private String reasonForRevocation;
	
	
	public RevocationDTO() {
		
	}

	public RevocationDTO(Long issuerId, String reasonForRevocation) {
		this.issuerId = issuerId;
		this.reasonForRevocation = reasonForRevocation;
	}

	public Long getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(Long issuerId) {
		this.issuerId = issuerId;
	}

	public String getReasonForRevocation() {
		return reasonForRevocation;
	}

	public void setReasonForRevocation(String reasonForRevocation) {
		this.reasonForRevocation = reasonForRevocation;
	}
}
