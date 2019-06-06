package com.xml.MegaTravelMBA.dto;

import java.util.Date;

public class StandardSearchDTO 
{
	 private Long cityID;
	 private Long countryID;
	 
	 private Date fromDate;
	 private Date endDate;
	 
	 
	 private int personCount;
	 
	 

	public StandardSearchDTO() {
		super();
	}

	public StandardSearchDTO(Long cityID, Long countryID, Date fromDate, Date endDate, int personCount) {
		super();
		this.cityID = cityID;
		this.countryID = countryID;
		this.fromDate = fromDate;
		this.endDate = endDate;
		this.personCount = personCount;
	}

	public Long getCityID() {
		return cityID;
	}

	public void setCityID(Long cityID) {
		this.cityID = cityID;
	}

	public Long getCountryID() {
		return countryID;
	}

	public void setCountryID(Long countryID) {
		this.countryID = countryID;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPersonCount() {
		return personCount;
	}

	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}
	 
	 
	 
	 
}
