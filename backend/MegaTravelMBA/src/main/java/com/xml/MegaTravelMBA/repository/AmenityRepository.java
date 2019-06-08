package com.xml.MegaTravelMBA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelMBA.model.Amenity;


public interface AmenityRepository extends JpaRepository<Amenity, Long> 
{
	  
	  Amenity findOneById(Long id);
	  
}
	 