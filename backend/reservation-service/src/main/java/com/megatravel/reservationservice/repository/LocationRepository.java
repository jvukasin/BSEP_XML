package com.megatravel.reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.reservationservice.model.Location;


public interface LocationRepository extends JpaRepository<Location, Long> 
{
	  Location findOneById(Long id);
}