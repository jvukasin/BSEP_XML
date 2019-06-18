package com.megatravel.accommodationservice.dto;

import java.util.Date;
import java.util.List;

import com.megatravel.accommodationservice.model.Amenity;


public class ExtendedSearchDTO 
{
	 private String city;
	 private Date startDate;
	 private Date endDate;
	 private Integer personCount;
	 private Double ratingAvg;
	 private List<Amenity> amenities;
	 private String type;
	 private String category;



	public ExtendedSearchDTO(String city, Date startDate, Date endDate, Integer personCount,
			Double ratingAvg, List<Amenity> amenities, String t, String category) {
		this.city = city;
		this.startDate = startDate;
		this.endDate = endDate;
		this.personCount = personCount;
		this.ratingAvg = ratingAvg;
		this.amenities = amenities;
		this.type = t;
		this.category = category;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public Integer getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
