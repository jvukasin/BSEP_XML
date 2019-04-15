package com.xmlbesp.MegaTravelPKI.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="idIssuer")
	private Long idIssuer;
	
	@Column(name="alias", nullable = false) 
	private String alias;
	
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
	
	@OneToOne(mappedBy = "certificate", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Software software;
	
	@Column(name="validationApi")
	private String validationApi;
	
	public Certificate() {
		
	}

	public Certificate(Long idIssuer, String alias, Date startDate, Date endDate, boolean ca) {
		super();
		this.idIssuer = idIssuer;
		this.alias = alias;
		this.startDate = startDate;
		this.endDate = endDate;
		this.ca = ca;
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

	public Software getSoftware() {
		return software;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}

	public String getValidationApi() {
		return validationApi;
	}

	public void setValidationApi(String validationApi) {
		this.validationApi = validationApi;
	}

}