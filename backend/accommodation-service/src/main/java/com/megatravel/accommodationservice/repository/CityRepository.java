package com.megatravel.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.accommodationservice.model.City;

import java.util.List;


public interface CityRepository extends JpaRepository<City, Long> 
{
	  City findOneById(Long id);

	  List<City> findByNameContainingIgnoreCase(String name);
}
	 