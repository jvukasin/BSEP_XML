package com.megatravel.accommodationservice.dto;

public class AccommodationInfDTO {

    private Long id;
    private String name;
    private String imageUrl;
    private LocationDTO location;
    private double rating;
    private int cancelPeriod;
    private int category;

    public AccommodationInfDTO() {
    }

    public AccommodationInfDTO(Long id , String name, String imageUrl, LocationDTO location, double rating, int cancelPeriod, int category) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.location = location;
        this.rating = rating;
        this.cancelPeriod = cancelPeriod;
        this.category = category;
    }

    public AccommodationInfDTO(AccommodationUnitDTO a) {
        this.id = a.getId();
        this.name = a.getName();
        this.imageUrl = null;
        this.location = a.getLocation();
        this.rating= a.getRatingAvg();
        this.cancelPeriod = a.getCancellationPeriod();
        this.category = a.getCategory();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCancelPeriod() {
        return cancelPeriod;
    }

    public void setCancelPeriod(int cancelPeriod) {
        this.cancelPeriod = cancelPeriod;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
