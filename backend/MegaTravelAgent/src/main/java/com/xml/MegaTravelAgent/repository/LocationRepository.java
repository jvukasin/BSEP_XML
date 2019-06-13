package com.xml.MegaTravelAgent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelAgent.model.Location;



public interface LocationRepository extends JpaRepository<Location, Long> 
{
	  Location findOneById(Long id);
}