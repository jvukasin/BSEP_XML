package com.xml.MegaTravelAgent.repository;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xml.MegaTravelAgent.model.AccommodationUnit;



  public interface AccommodationUnitRepository extends JpaRepository<AccommodationUnit, Long> 
  {
	  /*
		@Transactional
		@Query(value = "select distinct\n" + 
				"	accommodation_unit.*, @id := accommodation_unit.id\n" + 
				"from		\n" + 
				"	accommodation_unit join location on accommodation_unit.location_id = location.id\n" + 
				"where\n" + 
				"	 location.city_id = ?1 and\n" + 
				"     capacity <= ?2 and\n" + 
				"	 not exists \n" + 
				"		(select distinct * \n" + 
				"		 from \n" + 
				"			reservation\n" + 
				"		 where\n" + 
				"			reservation.accommodation_unit_id = @id and\n" + 
				"			(reservation.start_date <= ?3 and reservation.end_date >= ?3) or (reservation.start_date >= ?3 and reservation.start_date >= ?4)\n" + 
				"		)",nativeQuery = true)
		public Collection<AccommodationUnit> search( Long cityId,int capacity, Date start,Date end);
	*/
  }
 