package com.xml.MegaTravelMBA.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelMBA.model.AccommodationUnit;
import com.xml.MegaTravelMBA.model.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long> 
{

	Collection<Reservation> findOneByAccommodationUnit(AccommodationUnit accommodation);

}
