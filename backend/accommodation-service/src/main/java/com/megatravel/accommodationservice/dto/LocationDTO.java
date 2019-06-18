package com.megatravel.accommodationservice.dto;

import com.megatravel.accommodationservice.model.Location;

public class LocationDTO {

    private Long id;
    private String coordinates;
    private double distanceFromCity;
    private CityDTO city;

    public LocationDTO() {
    }

    public LocationDTO(Location l) {
        this(l.getId(), l.getCoordinates(), l.getDistanceFromCity(), new CityDTO(l.getCity()));
    }

    public LocationDTO(Long id, String coordinates, double distanceFromCity, CityDTO city) {
        this.id = id;
        this.coordinates = coordinates;
        this.distanceFromCity = distanceFromCity;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public double getDistanceFromCity() {
        return distanceFromCity;
    }

    public void setDistanceFromCity(double distanceFromCity) {
        this.distanceFromCity = distanceFromCity;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }
}
