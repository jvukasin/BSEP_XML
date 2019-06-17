package com.megatravel.reservationservice.dto;

import java.util.List;

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
    private List<AmenityDTO> amenities;
    private List<ImageDTO> images;
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
