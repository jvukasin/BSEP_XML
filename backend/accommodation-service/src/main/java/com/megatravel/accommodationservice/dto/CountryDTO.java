package com.megatravel.accommodationservice.dto;

import com.megatravel.accommodationservice.model.Country;

public class CountryDTO {

    private Long id;
    private String name;

    public CountryDTO() {
    }

    public CountryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CountryDTO(Country country)
    {
    	id = country.getId();
    	name = country.getName();
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
}
