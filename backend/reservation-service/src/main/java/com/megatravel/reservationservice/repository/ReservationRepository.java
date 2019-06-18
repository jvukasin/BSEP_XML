package com.megatravel.reservationservice.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.megatravel.reservationservice.model.AccommodationUnit;
import com.megatravel.reservationservice.model.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long> 
{

	Collection<Reservation> findOneByAccommodationUnit(AccommodationUnit accommodation);

	@Modifying
	@Query("UPDATE reservation \r\n" + 
			"SET is_successful = true\r\n" + 
			"WHERE id = :reservationID")
	void setSuccessful(@Param("reservationID") Long id);

}
