package com.megatravel.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.accommodationservice.model.Amenity;

import java.util.List;


public interface AmenityRepository extends JpaRepository<Amenity, Long>
{
    List<Amenity> findAll();
}
	 