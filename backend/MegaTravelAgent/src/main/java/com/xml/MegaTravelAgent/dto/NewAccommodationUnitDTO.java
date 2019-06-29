package com.xml.MegaTravelAgent.dto;

import java.util.List;

public class NewAccommodationUnitDTO {

    private String name;
    private String description;
    private String type;
    private int capacity;
    private int category;
    private int cancellationPeriod;
    private double defaultPrice;
    private LocationDTO location;
    private List<AmenityDTO> amenities;
    private List<SpecificPriceDTO> specificPrices;
    private List<ImageDTO> images;

    public NewAccommodationUnitDTO() {
    }

    public NewAccommodationUnitDTO(String name, String description, String type, int capacity, int category, int cancellationPeriod, double defaultPrice, LocationDTO location, List<AmenityDTO> amenities, List<SpecificPriceDTO> specificPrices, List<ImageDTO> images) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.capacity = capacity;
        this.category = category;
        this.cancellationPeriod = cancellationPeriod;
        this.defaultPrice = defaultPrice;
        this.location = location;
        this.amenities = amenities;
        this.specificPrices = specificPrices;
        this.images = images;
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

    public double getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(double defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public List<AmenityDTO> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<AmenityDTO> amenities) {
        this.amenities = amenities;
    }

    public List<SpecificPriceDTO> getSpecificPrices() {
        return specificPrices;
    }

    public void setSpecificPrices(List<SpecificPriceDTO> specificPrices) {
        this.specificPrices = specificPrices;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
