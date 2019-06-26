package com.xml.MegaTravelAgent.service;

import com.xml.MegaTravelAgent.dto.ReservationDTO;
import com.xml.MegaTravelAgent.exceptions.BusinessException;
import com.xml.MegaTravelAgent.model.AccommodationUnit;
import com.xml.MegaTravelAgent.model.Reservation;
import com.xml.MegaTravelAgent.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepo;

    public List<ReservationDTO> updateReservations(Collection<Reservation> reservations, String agentUsername) {

        Collection<Reservation> oldReservations = reservationRepo.findAllByAgentUsername(agentUsername);

        for (Reservation oldReservation: oldReservations) {
            boolean exists = false;

            for (Reservation newReservation: reservations) {
                if (newReservation.getId().equals(oldReservation.getId())) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                reservationRepo.deleteById(oldReservation.getId());
            }
        }

        List<ReservationDTO> reservationDTOs = new ArrayList<>();

        for (Reservation newReservation: reservations) {
            Reservation oldReservation;

            try {
                oldReservation = reservationRepo.findById(newReservation.getId()).get();
            } catch (NoSuchElementException e) {

                if (newReservation.getUsernameReservator().equals(agentUsername)) {
                    newReservation.setSelfReserved(true);
                } else {
                    newReservation.setSelfReserved(false);
                }

                oldReservation = reservationRepo.save(newReservation);
            }

            reservationDTOs.add(new ReservationDTO(oldReservation));
        }

        return reservationDTOs;

    }

    public Long setSuccessful(Long id)
    {
        try
        {
            Reservation reservation = reservationRepo.findById(id).get();

            reservation.setIsSuccessful(true);

            reservationRepo.setSuccessful(id);

            return id;
        }
        catch(NoSuchElementException e)
        {
            throw new BusinessException("No reservation with id: " + id + " found.");
        }
    }

    public Reservation formReservationForSOAP(ReservationDTO reservationDTO, String usernameAgent) {

        AccommodationUnit au = new AccommodationUnit();
        au.setId(reservationDTO.getAccommodationUnitId());

        Reservation r = new Reservation();

        r.setAccommodationUnit(au);
        r.setStartDate(reservationDTO.getStartDate());
        r.setEndDate(reservationDTO.getEndDate());
        r.setUsernameReservator(usernameAgent);

        return r;

    }
}
