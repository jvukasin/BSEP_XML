package com.megatravel.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.accommodationservice.model.City;


public interface CityRepository extends JpaRepository<City, Long> 
{
	  City findOneById(Long id);

	  City findOneByName(String name);
}
	 