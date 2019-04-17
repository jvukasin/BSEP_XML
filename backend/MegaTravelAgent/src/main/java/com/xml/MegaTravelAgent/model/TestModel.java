package com.xml.MegaTravelAgent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TestModel {

	@Id
	@Column(name = "id", nullable = false)
	private Long id;
	
	public TestModel() {
		
	}

	public TestModel(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
