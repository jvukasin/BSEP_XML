package com.xmlbesp.MegaTravelPKI.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Communication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "idA", nullable = false)
	private Long idA;
	
	@Column(name = "idB", nullable = false)
	private Long idB;
	
	public Communication() {
		super();
	}
	
	

	public Communication(Long idA, Long idB) {
		
		this.idA = idA;
		this.idB = idB;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getidA() {
		return idA;
	}

	public void setidA(Long idA) {
		this.idA = idA;
	}

	public Long getidB() {
		return idB;
	}

	public void setidB(Long idB) {
		this.idB = idB;
	}
	
	
	
}
