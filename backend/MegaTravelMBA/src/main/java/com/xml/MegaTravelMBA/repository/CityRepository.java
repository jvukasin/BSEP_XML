package com.xml.MegaTravelMBA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelMBA.model.City;

public interface CityRepository extends JpaRepository<City, Long> 
{
	  City findOneById(Long id);
}
	 