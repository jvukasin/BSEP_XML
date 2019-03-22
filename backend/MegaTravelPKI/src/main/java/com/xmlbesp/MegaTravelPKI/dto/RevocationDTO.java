package com.xmlbesp.MegaTravelPKI.dto;

public class RevocationDTO {
	
	private Long subjectId;
	private String reasonForRevocation;
	
	
	public RevocationDTO() {
		
	}
	
	public RevocationDTO(Long subjectId, String reasonForRevocation) {
		super();
		this.subjectId = subjectId;
		this.reasonForRevocation = reasonForRevocation;
	}
	
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	public String getReasonForRevocation() {
		return reasonForRevocation;
	}
	public void setReasonForRevocation(String reasonForRevocation) {
		this.reasonForRevocation = reasonForRevocation;
	}
	
	

}
