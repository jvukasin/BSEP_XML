package com.xml.MegaTravelMBA.dto;

import java.util.Date;
import java.util.List;

import com.xml.MegaTravelMBA.model.Amenity;

public class ExtendedSearchDTO 
{
	 private Long cityID;
	 private Long countryID;
	 
	 private Date fromDate;
	 private Date endDate;
	 
	 private int personCount;
	 
	 private double ratingAvg;
	 private List<Amenity> amenities;

	public ExtendedSearchDTO(Long cityID, Long countryID, Date fromDate, Date endDate, int personCount,
			double ratingAvg, List<Amenity> amenities) {
		super();
		this.cityID = cityID;
		this.countryID = countryID;
		this.fromDate = fromDate;
		this.endDate = endDate;
		this.personCount = personCount;
		this.ratingAvg = ratingAvg;
		this.amenities = amenities;
	}

	public double getRatingAvg() {
		return ratingAvg;
	}

	public void setRatingAvg(double ratingAvg) {
		this.ratingAvg = ratingAvg;
	}

	public List<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<Amenity> amenities) {
		this.amenities = amenities;
	}

	public ExtendedSearchDTO() {
		super();
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
