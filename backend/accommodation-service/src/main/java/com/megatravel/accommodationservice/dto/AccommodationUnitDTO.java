package com.megatravel.accommodationservice.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.megatravel.accommodationservice.model.AccommodationUnit;
import com.megatravel.accommodationservice.model.Amenity;
import com.megatravel.accommodationservice.model.Image;
import com.megatravel.accommodationservice.model.SpecificPrice;


public class AccommodationUnitDTO {

    private Long id;
    private String name;
    private String description;
    private String type;
    private int capacity;
    private int cancellationPeriod;
    private double price;
    private double ratingAvg;
    private int category;
    private List<AmenityDTO> amenities = new ArrayList<>();
    private List<ImageDTO> images = new ArrayList<>();
    private LocationDTO location;
    private UserInfoDTO agent;

    public AccommodationUnitDTO() {
    }

    public AccommodationUnitDTO(Long id, String name, String description, String type, int capacity, int cancellationPeriod, double price,
                                double ratingAvg, int category, List<AmenityDTO> amenities, List<ImageDTO> images, LocationDTO location,
                                UserInfoDTO agent) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.capacity = capacity;
        this.cancellationPeriod = cancellationPeriod;
        this.price = price;
        this.ratingAvg = ratingAvg;
        this.category = category;
        this.amenities = amenities;
        this.images = images;
        this.location = location;
        this.agent = agent;
    }
    
    public AccommodationUnitDTO(AccommodationUnit accommodation)
    {
    	
    	id = accommodation.getId();
    	name = accommodation.getName();
    	description = accommodation.getDescription();
    	type = accommodation.getType();
    	capacity = accommodation.getCapacity();
    	cancellationPeriod = accommodation.getCancellationPeriod();
    	price = findCurrentPrice(accommodation);
    	ratingAvg = accommodation.getRatingAvg();
    	category = accommodation.getCategory();
    	
    	for(Amenity amenity : accommodation.getAmenity())
    	{
    		amenities.add(new AmenityDTO(amenity));
    	}
    	
    	for(Image image : accommodation.getImage())
    	{
    		images.add(new ImageDTO(image));
    	}
    	
    	location = new LocationDTO(accommodation.getLocation());
    	//agent = new UserInfoDTO(accommodation.getAgent());
    	
    }

    private double findCurrentPrice(AccommodationUnit accommodation) 
    {
    	Date today = new Date();
    	double retVal = -1;
    	
		for(SpecificPrice specificPrice : accommodation.getSpecificPrice())
		{
			if(isInSpecificPrice(today,specificPrice))
			{
				retVal = specificPrice.getPrice();
			}
		}
    	
		if(retVal == -1)
		{
			retVal = accommodation.getDefaultPrice();
		}
		
    	return retVal;
	}
    
	private boolean isInSpecificPrice(Date currentDay, SpecificPrice specificPrice) 
	{
		if(currentDay.getTime() >= specificPrice.getStartDate().getTime() 
		   && 
		   currentDay.getTime() <= specificPrice.getEndDate().getTime())
		{
			return true;
		}
		else
		{
			return false;
		}
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCancellationPeriod() {
        return cancellationPeriod;
    }

    public void setCancellationPeriod(int cancellationPeriod) {
        this.cancellationPeriod = cancellationPeriod;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRatingAvg() {
        return ratingAvg;
    }

    public void setRatingAvg(double ratingAvg) {
        this.ratingAvg = ratingAvg;
    }

    public List<AmenityDTO> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<AmenityDTO> amenities) {
        this.amenities = amenities;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public UserInfoDTO getAgent() {
        return agent;
    }

    public void setAgent(UserInfoDTO agent) {
        this.agent = agent;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
