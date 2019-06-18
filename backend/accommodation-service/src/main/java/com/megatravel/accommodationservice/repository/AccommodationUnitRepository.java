package com.megatravel.accommodationservice.repository;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.megatravel.accommodationservice.model.AccommodationUnit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AccommodationUnitRepository extends JpaRepository<AccommodationUnit, Long>
  {
	  
	  
		@Transactional
		@Query(value = "select distinct\n" + 
				"	accommodation_unit.*, @id := accommodation_unit.id\n" + 
				"from		\n" + 
				"	accommodation_unit join location on accommodation_unit.location_id = location.id\n" + 
				"where\n" + 
				"	 location.city_id = :cityId and\n" + 
				"     capacity >= :capacity and\n" +
				"	 not exists \n" + 
				"		(select distinct * \n" + 
				"		 from \n" + 
				"			reservation\n" + 
				"		 where\n" + 
				"			reservation.accommodation_unit_id = @id and\n" + 
				"			(reservation.start_date <= :start and reservation.end_date >= :start) or (reservation.start_date >= :start and reservation.start_date >= :end)\n" + 
				"		)",nativeQuery = true)
		public Collection<AccommodationUnit> search(@Param("cityId") Long cityId, @Param("capacity") int capacity, @Param("start") Date start, @Param("end") Date end);
		
		
  }
 