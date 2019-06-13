package com.xml.MegaTravelAgent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelAgent.model.City;


public interface CityRepository extends JpaRepository<City, Long> 
{
	  City findOneById(Long id);
}
	 