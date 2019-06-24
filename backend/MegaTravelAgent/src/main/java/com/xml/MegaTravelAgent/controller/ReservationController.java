package com.xml.MegaTravelAgent.controller;

import com.xml.MegaTravelAgent.dto.AccommodationUnitDTO;
import com.xml.MegaTravelAgent.dto.ReservationDTO;
import com.xml.MegaTravelAgent.model.Reservation;
import com.xml.MegaTravelAgent.security.TokenUtils;
import com.xml.MegaTravelAgent.soap.client.ReservationClient;
import com.xml.MegaTravelAgent.soap.reqres.FetchReservationsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/reservations")
public class ReservationController {


    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    ReservationClient reservationClient;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Collection<ReservationDTO>> getReservations(HttpServletRequest request)
    {

        String username = getUsernameFromRequest(request);

        if (username == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }


        FetchReservationsResponse response = reservationClient.fetchAgentsReservations(username);

        Collection<ReservationDTO> reservations = new ArrayList<>();

        for (Reservation r: response.getReservation()) {
            reservations.add(new ReservationDTO(r));

        }

        return new ResponseEntity<Collection<ReservationDTO>>(reservations,
                HttpStatus.OK);
    }

    private String getUsernameFromRequest(HttpServletRequest request) {

        String authToken = tokenUtils.getToken(request);
        if (authToken == null) {
            return null;
        }

        String username = tokenUtils.getUsernameFromToken(authToken);

        return username;
    }
}
