package com.xml.MegaTravelMBA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelMBA.model.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long> 
{
	  Reservation findOneById(Long id);
}
