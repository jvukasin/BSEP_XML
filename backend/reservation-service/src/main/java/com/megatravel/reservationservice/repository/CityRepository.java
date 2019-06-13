package com.megatravel.reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.reservationservice.model.City;

public interface CityRepository extends JpaRepository<City, Long> 
{
	  City findOneById(Long id);
}
	 