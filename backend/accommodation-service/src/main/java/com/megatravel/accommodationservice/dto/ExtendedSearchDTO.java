package com.megatravel.accommodationservice.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.megatravel.accommodationservice.model.Amenity;


public class ExtendedSearchDTO 
{
	 private String city;
	 
	 private Date fromDate;
	 private Date endDate;
	 
	 private int personCount;
	 
	 private double ratingAvg = -1;
	 private List<Amenity> amenities = new ArrayList<Amenity>();
	 private String type;
	 private double distanceFromCity = -1;
	 private int cancellationPeriod;
	 private String category;


	public ExtendedSearchDTO(String city, Date fromDate, Date endDate, int personCount,
			double ratingAvg, List<Amenity> amenities, String t, double distance, int cancel, String category) {
		super();
		this.city = city;
		this.fromDate = fromDate;
		this.endDate = endDate;
		this.personCount = personCount;
		this.ratingAvg = ratingAvg;
		this.amenities = amenities;
		this.distanceFromCity = distance;
		this.type = t;
		this.cancellationPeriod = cancel;
		this.category = category;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getFromDate() {
		return fromDate;
	}

	@SuppressWarnings("deprecation")
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
		this.fromDate.setHours(0);
		this.fromDate.setMinutes(0);
		this.fromDate.setSeconds(0);
	}

	public Date getEndDate() {
		return endDate;
	}

	@SuppressWarnings("deprecation")
	public void setEndDate(Date endDate) 
	{
		this.endDate = endDate;
		this.endDate.setHours(0);
		this.endDate.setMinutes(0);
		this.endDate.setSeconds(0);
	}

	public int getPersonCount() {
		return personCount;
	}

	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}

	public int getCancellationPeriod() {
		return cancellationPeriod;
	}

	public void setCancellationPeriod(int cancellationPeriod) {
		this.cancellationPeriod = cancellationPeriod;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
