package com.xmlbesp.MegaTravelPKI.dto;

import com.xmlbesp.MegaTravelPKI.model.Software;

public class SoftwareDTO {
	
	private Long id;
	private String name;
	private boolean certified;
	
	public SoftwareDTO() {
		
	}
	
	public SoftwareDTO(Software s) {
		this(s.getId(), s.getName(), s.isCertified());
	}
	
	public SoftwareDTO(Long id, String name, boolean certified) {
		super();
		this.id = id;
		this.name = name;
		this.certified = certified;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCertified() {
		return certified;
	}

	public void setCertified(boolean certified) {
		this.certified = certified;
	}
}
