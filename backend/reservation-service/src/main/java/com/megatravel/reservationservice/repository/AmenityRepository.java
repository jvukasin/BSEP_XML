package com.megatravel.reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.reservationservice.model.Amenity;



public interface AmenityRepository extends JpaRepository<Amenity, Long> 
{
	  
	  Amenity findOneById(Long id);
	  
}
	 