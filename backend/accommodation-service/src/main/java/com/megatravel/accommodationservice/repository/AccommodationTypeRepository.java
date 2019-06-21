package com.megatravel.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.accommodationservice.model.AccommodationType;

public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, String> 
{
	
}
