package com.megatravel.accommodationservice.dto;

import com.megatravel.accommodationservice.model.City;

public class CityDTO {

    private Long id;
    private String name;
    private CountryDTO country;

    public CityDTO() {
    }

    public CityDTO(Long id, String name, CountryDTO country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
    
    public CityDTO(City city)
    {
    	id = city.getId();
    	name = city.getName();
    	country = new CountryDTO(city.getCountry());
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

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }
}
