package com.xml.MegaTravelAgent.dto;

import com.xml.MegaTravelAgent.model.Amenity;

import java.util.ArrayList;
import java.util.List;

public class AccommodationSettingsDTO {

    private List<AmenityDTO> amenities = new ArrayList<>();
    private List<String> accommodationTypes = new ArrayList<>();

    public AccommodationSettingsDTO() {

    }

    public List<AmenityDTO> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<AmenityDTO> amenities) {
        this.amenities = amenities;
    }

    public List<String> getAccommodationTypes() {
        return accommodationTypes;
    }

    public void setAccommodationTypes(List<String> accommodationTypes) {
        this.accommodationTypes = accommodationTypes;
    }
}
