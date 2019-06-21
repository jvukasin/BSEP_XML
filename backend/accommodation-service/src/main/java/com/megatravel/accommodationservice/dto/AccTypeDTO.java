package com.megatravel.accommodationservice.dto;

import com.megatravel.accommodationservice.model.AccommodationType;

public class AccTypeDTO {

    private String name;

    public AccTypeDTO() {

    }

    public AccTypeDTO(String name) {
        this.name = name;
    }

    public AccTypeDTO(AccommodationType a) {
        this.name = a.getType();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
