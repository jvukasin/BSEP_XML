package com.xml.MegaTravelAgent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelAgent.model.Amenity;



public interface AmenityRepository extends JpaRepository<Amenity, Long> 
{
	  
	  Amenity findOneById(Long id);
	  
}
	 