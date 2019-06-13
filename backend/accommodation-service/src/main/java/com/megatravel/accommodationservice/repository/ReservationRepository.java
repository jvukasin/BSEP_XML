package com.megatravel.accommodationservice.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.accommodationservice.model.AccommodationUnit;
import com.megatravel.accommodationservice.model.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long> 
{

	Collection<Reservation> findOneByAccommodationUnit(AccommodationUnit accommodation);

}
