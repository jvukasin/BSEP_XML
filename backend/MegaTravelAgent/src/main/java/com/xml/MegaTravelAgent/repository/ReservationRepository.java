package com.xml.MegaTravelAgent.repository;

import java.util.Collection;

import com.xml.MegaTravelAgent.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelAgent.model.AccommodationUnit;
import org.springframework.data.jpa.repository.Query;


public interface ReservationRepository extends JpaRepository<Reservation, Long> 
{

	Collection<Reservation> findOneByAccommodationUnit(AccommodationUnit accommodation);

	@Query("select reservation from Reservation reservation, AccommodationUnit au where reservation.accommodationUnit" +
			".id = au.id and au.agent.username = :username")
	Collection<Reservation> findAllByAgentUsername(String username);

}
