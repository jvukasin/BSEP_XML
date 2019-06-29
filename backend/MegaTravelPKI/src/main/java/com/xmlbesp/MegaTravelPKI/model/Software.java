package com.xmlbesp.MegaTravelPKI.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Software {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column
	private boolean certified;

	@OneToOne
	private Certificate certificate;

	@ManyToOne(cascade = {CascadeType.ALL})
	private Software software;

	@OneToMany(mappedBy = "software")
	private Set<Software> trustedSoftwares = new HashSet<>();

	public Software() {
		super();
	}

	public Software(String name) {
		super();
		this.name = name;
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

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public Software getSoftware() {
		return software;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}

	public Set<Software> getTrustedSoftwares() {
		return trustedSoftwares;
	}

	public void setTrustedSoftwares(Set<Software> trustedSoftwares) {
		this.trustedSoftwares = trustedSoftwares;
	}
}