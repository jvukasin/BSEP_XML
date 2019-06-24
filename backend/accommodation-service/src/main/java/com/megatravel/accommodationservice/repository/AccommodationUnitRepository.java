package com.megatravel.accommodationservice.repository;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import com.megatravel.accommodationservice.dto.AccommodationUnitDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.megatravel.accommodationservice.model.AccommodationUnit;


import java.util.List;


public interface AccommodationUnitRepository extends JpaRepository<AccommodationUnit, Long>
  {

  		AccommodationUnit findOneById(Long id);
	  
	  
		@Transactional
		@Query(value = "select distinct\n" + 
				"	acc.*" +
				"from		\n" + 
				"	accommodation_unit acc join location on acc.location_id = location.id\n" +
				"where\n" + 
				"	 location.city_id=:cityId and\n" +
				"    capacity = :capacity",nativeQuery = true)
		public List<AccommodationUnit> search(@Param("cityId") Long cityId, @Param("capacity") int capacity);

		
		
  }
 