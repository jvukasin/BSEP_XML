package com.xmlbesp.MegaTravelPKI.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="idIssuer",nullable = false)
	private Long idIssuer;
	
	@Column(name="idSubject",nullable = false)
	private Long idSubject;
	
	@Column(name="startDate",nullable = false)
	private Date startDate;
	
	@Column(name="endDate",nullable = false)
	private Date endDate;
	
	@Column(name="revoked")
	private boolean revoked;
	
	@Column(name="ca")
	private boolean ca;
	
	@Column(name="reasonForRevocation",nullable = false)
	private String reasonForRevocation;

	public Certificate() {

	}

	public Certificate(Long idIssuer, Long idSubject, Date startDate, Date endDate, boolean revoked, boolean ca, String reasonForRevocation) {
		super();
		this.idIssuer = idIssuer;
		this.idSubject = idSubject;
		this.startDate = startDate;
		this.endDate = endDate;
		this.revoked = revoked;
		this.ca = ca;
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

	public String getReasonForRevocation() {
		return reasonForRevocation;
	}

	public void setReasonForRevocation(String reasonForRevocation) {
		this.reasonForRevocation = reasonForRevocation;
	}

	public boolean isCa() {
		return ca;
	}

	public void setCa(boolean ca) {
		this.ca = ca;
	}
}