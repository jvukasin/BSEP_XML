package com.megatravel.reservationservice.dto;

import com.megatravel.reservationservice.model.Amenity;

public class AmenityDTO {

    private Long id;
    private String name;
    private String faIcon;

    public AmenityDTO() {
    }

    public AmenityDTO(Long id, String name, String faIcon) {
        this.id = id;
        this.name = name;
        this.faIcon = faIcon;
    }
    
    public AmenityDTO(Amenity amenity)
    {
    	id = amenity.getId();
    	name = amenity.getName();
    	faIcon = amenity.getFaIcon();
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

    public String getFaIcon() {
        return faIcon;
    }

    public void setFaIcon(String faIcon) {
        this.faIcon = faIcon;
    }
}
