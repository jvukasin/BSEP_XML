package com.xml.MegaTravelAgent.controller;

import com.xml.MegaTravelAgent.dto.ReservationDTO;
import com.xml.MegaTravelAgent.model.Reservation;
import com.xml.MegaTravelAgent.security.TokenUtils;
import com.xml.MegaTravelAgent.service.ReservationService;
import com.xml.MegaTravelAgent.soap.client.ReservationClient;
import com.xml.MegaTravelAgent.soap.reqres.FetchReservationsResponse;
import com.xml.MegaTravelAgent.soap.reqres.SuccessReservationRequest;
import com.xml.MegaTravelAgent.soap.reqres.SuccessReservationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

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

        List<ReservationDTO> reservationDTOs = reservationService.updateReservations(response.getReservation(),
                username);

        return new ResponseEntity<Collection<ReservationDTO>>(reservationDTOs,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/success", method = RequestMethod.PUT)
    public ResponseEntity<String> successReservation(@PathVariable Long id,
                                                                         HttpServletRequest request)
    {

        String username = getUsernameFromRequest(request);

        if (username == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        SuccessReservationResponse response = reservationClient.successReservation(id, username);

        String responseInfo = response.getResponseInfo();

        if (responseInfo.equals("success")) {
            reservationService.setSuccessful(id);
        }

        return new ResponseEntity<>(responseInfo,
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
