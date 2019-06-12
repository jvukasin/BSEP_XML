package repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.reservationservice.AccommodationUnit;
import com.megatravel.reservationservice.model.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long> 
{

	Collection<Reservation> findOneByAccommodationUnit(AccommodationUnit accommodation);

}
