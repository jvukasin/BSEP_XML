package com.xml.MegaTravelAgent.dto;

import com.xml.MegaTravelAgent.model.Amenity;

import java.util.ArrayList;
import java.util.List;

public class AccommodationSettingsDTO {

    public List<Amenity> amenities = new ArrayList<>();
    public List<String> accommodationTypes = new ArrayList<>();

    public AccommodationSettingsDTO() {

    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public List<String> getAccommodationTypes() {
        return accommodationTypes;
    }

    public void setAccommodationTypes(List<String> accommodationTypes) {
        this.accommodationTypes = accommodationTypes;
    }
}
