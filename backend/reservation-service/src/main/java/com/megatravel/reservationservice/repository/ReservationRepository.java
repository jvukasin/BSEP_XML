package com.megatravel.reservationservice.repository;

import java.util.Collection;
import java.util.List;

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
	@Query("UPDATE Reservation \r\n" + 
			"SET is_successful = true\r\n" + 
			"WHERE id = :reservationID")
	void setSuccessful(@Param("reservationID") Long reservationID);

	@Query("select reservation from Reservation reservation where reservation.reservator.username = :username")
	List<Reservation> getUserReservations(@Param("username") String username);

	@Query("select reservation from Reservation reservation, AccommodationUnit au where reservation.accommodationUnit" +
			".id = au.id and au.agent.username = :username")
	List<Reservation> getAgentReservations(@Param("username") String username);


}
