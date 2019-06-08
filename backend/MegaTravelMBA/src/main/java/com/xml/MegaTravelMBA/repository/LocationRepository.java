package com.xml.MegaTravelMBA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelMBA.model.Location;


public interface LocationRepository extends JpaRepository<Location, Long> 
{
	  Location findOneById(Long id);
}