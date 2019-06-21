package com.megatravel.reservationservice.dto;

public class AccommodationInfoDTO {

    private String name;
    private String imageUrl;
    private LocationDTO location;
    private double rating;
    //dodati kategoriju kad se sredi kao posebna klasa


    public AccommodationInfoDTO() {
    }

    public AccommodationInfoDTO(String name, String imageUrl, LocationDTO location, double rating) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.location = location;
        this.rating = rating;
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
}
