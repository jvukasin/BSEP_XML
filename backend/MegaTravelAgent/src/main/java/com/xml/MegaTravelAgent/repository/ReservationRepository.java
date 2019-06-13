package com.xml.MegaTravelAgent.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelAgent.model.AccommodationUnit;
import com.xml.MegaTravelAgent.model.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long> 
{

	Collection<Reservation> findOneByAccommodationUnit(AccommodationUnit accommodation);

}
