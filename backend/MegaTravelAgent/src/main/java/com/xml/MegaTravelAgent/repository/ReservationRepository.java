package com.xml.MegaTravelAgent.repository;

import java.util.Collection;

import com.xml.MegaTravelAgent.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelAgent.model.AccommodationUnit;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface ReservationRepository extends JpaRepository<Reservation, Long> 
{

	Collection<Reservation> findOneByAccommodationUnit(AccommodationUnit accommodation);

	@Query("select reservation from Reservation reservation, AccommodationUnit au where reservation.accommodationUnit" +
			".id = au.id and au.agent.username = :username")
	Collection<Reservation> findAllByAgentUsername(String username);

	@Transactional
	@Modifying
	@Query("UPDATE Reservation \r\n" +
			"SET is_successful = true\r\n" +
			"WHERE id = :reservationID")
	void setSuccessful(@Param("reservationID") Long reservationID);

}
