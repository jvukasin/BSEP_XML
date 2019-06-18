package com.megatravel.accommodationservice.dto;

import java.util.Date;
import java.util.List;

import com.megatravel.accommodationservice.model.Amenity;


public class ExtendedSearchDTO 
{
	 private Long cityID;
	 
	 private Date fromDate;
	 private Date endDate;
	 
	 private int personCount = -1;
	 
	 private double ratingAvg = -1;
	 private List<Amenity> amenities;
	 private String type;
	 private double distanceFromCity = -1;



	public ExtendedSearchDTO(Long cityID, Date fromDate, Date endDate, int personCount,
			double ratingAvg, List<Amenity> amenities, String t, double distance) {
		super();
		this.cityID = cityID;
		this.fromDate = fromDate;
		this.endDate = endDate;
		this.personCount = personCount;
		this.ratingAvg = ratingAvg;
		this.amenities = amenities;
		this.distanceFromCity = distance;
		this.type = t;
	}
	
	public double getDistanceFromCity() {
		return distanceFromCity;
	}

	public void setDistanceFromCity(double distanceFromCity) {
		this.distanceFromCity = distanceFromCity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
