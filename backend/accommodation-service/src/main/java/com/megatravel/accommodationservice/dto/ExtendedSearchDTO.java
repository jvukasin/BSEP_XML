package com.megatravel.accommodationservice.dto;

import java.util.Date;
import java.util.List;

import com.megatravel.accommodationservice.model.Amenity;


public class ExtendedSearchDTO 
{
	 private Long cityID;
	 
	 private Date fromDate;
	 private Date endDate;
	 
	 private Integer personCount;
	 
	 private Double ratingAvg;
	 private List<Amenity> amenities;
	 private String type;



	public ExtendedSearchDTO(Long cityID, Date fromDate, Date endDate, Integer personCount,
			Double ratingAvg, List<Amenity> amenities, String t) {
		super();
		this.cityID = cityID;
		this.fromDate = fromDate;
		this.endDate = endDate;
		this.personCount = personCount;
		this.ratingAvg = ratingAvg;
		this.amenities = amenities;
		this.type = t;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getRatingAvg() {
		return ratingAvg;
	}

	public void setRatingAvg(Double ratingAvg) {
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

	public Integer getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}
}
