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
	
	@Column(name="serialNumber",nullable = false)
	private String serialNumber;

	@Column(name="idIssuer",nullable = false)
	private Long idIssuer;
	
	@Column(name="idSubject",nullable = false)
	private Long idSubject;
	
	@Column(name="startDate",nullable = false)
	private Date startDate;
	
	@Column(name="endDate",nullable = false)
	private Date endDate;
	
	@Column
	private boolean revoked;
	
	@Column
	private boolean ca;
	
	@Column(name="reasonForRevokation",nullable = false)
	private String reasonForRevokation;

	public Certificate() {

	}

	public Certificate(String serialNumber, Long idIssuer, Long idSubject, Date startDate, Date endDate, boolean revoked, boolean ca, String reasonForRevokation) {
		super();
		this.serialNumber = serialNumber;
		this.idIssuer = idIssuer;
		this.idSubject = idSubject;
		this.startDate = startDate;
		this.endDate = endDate;
		this.revoked = revoked;
		this.ca = ca;
		this.reasonForRevokation = reasonForRevokation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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

	public String getReasonForRevokation() {
		return reasonForRevokation;
	}

	public void setReasonForRevokation(String reasonForRevokation) {
		this.reasonForRevokation = reasonForRevokation;
	}

	public boolean isCa() {
		return ca;
	}

	public void setCa(boolean ca) {
		this.ca = ca;
	}
}