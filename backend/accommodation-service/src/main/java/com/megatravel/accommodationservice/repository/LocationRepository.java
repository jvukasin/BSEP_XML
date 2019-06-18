package com.megatravel.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.accommodationservice.model.Location;
import org.springframework.data.jpa.repository.Query;


public interface LocationRepository extends JpaRepository<Location, Long>
{
	  Location findOneById(Long id);

	  @Query("select l from Location l where l.city.id = :id")
	  Location findOneByCityId(Long id);
}