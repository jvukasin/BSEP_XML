package com.xml.MegaTravelMBA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelMBA.model.AccommodationUnit;
import com.xml.MegaTravelMBA.model.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long> 
{
	/*
	@Modifying
	@Transactional
	@Query("select from Reservation where ")
	public void updateAirline(Integer id, String naziv, Integer adresa, String opis);
	*/
}
