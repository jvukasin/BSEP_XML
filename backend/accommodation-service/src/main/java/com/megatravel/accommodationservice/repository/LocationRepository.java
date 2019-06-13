package com.megatravel.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.accommodationservice.model.Location;



public interface LocationRepository extends JpaRepository<Location, Long> 
{
	  Location findOneById(Long id);
}