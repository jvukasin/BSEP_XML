package com.xml.MegaTravelAgent.controller;

import com.xml.MegaTravelAgent.dto.ChatDTO;
import com.xml.MegaTravelAgent.dto.MessageDTO;
import com.xml.MegaTravelAgent.dto.ReservationDTO;
import com.xml.MegaTravelAgent.exceptions.BusinessException;
import com.xml.MegaTravelAgent.model.Message;
import com.xml.MegaTravelAgent.model.Reservation;
import com.xml.MegaTravelAgent.security.TokenUtils;
import com.xml.MegaTravelAgent.service.Logging;
import com.xml.MegaTravelAgent.service.ReservationService;
import com.xml.MegaTravelAgent.soap.client.ReservationClient;
import com.xml.MegaTravelAgent.soap.reqres.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

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

    private Logging logger = new Logging(this);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Collection<ReservationDTO>> getReservations(HttpServletRequest request)
    {

        String username = getUsernameFromRequest(request);

        if (username == null) {
            logger.logError("RES_GET_USRERR; IP: " + request.getRemoteAddr());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }


        FetchReservationsResponse response = reservationClient.fetchAgentsReservations(username);

        List<ReservationDTO> reservationDTOs = reservationService.updateReservations(response.getReservation(),
                username);

        return new ResponseEntity<Collection<ReservationDTO>>(reservationDTOs,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getReservations(@PathVariable Long id, HttpServletRequest request)
    {

        String username = getUsernameFromRequest(request);

        if (username == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try
        {
            return new ResponseEntity<ReservationDTO>(reservationService.findById(id, username), HttpStatus.OK);
        }
        catch(BusinessException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservationDTO,
                                                                        HttpServletRequest request) {

        logger.logInfo("RES_CREATE");
        String username = getUsernameFromRequest(request);

        if (username == null) {
            logger.logError("RES_CREATE_USRERR; IP: " + request.getRemoteAddr());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }



        Reservation r = reservationService.formReservationForSOAP(reservationDTO, username);


        try {
            PostReservationResponse response = reservationClient.postReservation(r, username);
        } catch (Exception e) {
            e.printStackTrace();
            logger.logError("RES_CREATE_ERR: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        // todo insert maybe
        logger.logInfo("RES_CREATE_SUCCESS");
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}/success", method = RequestMethod.PUT)
    public ResponseEntity<String> successReservation(@PathVariable Long id,
                                                                         HttpServletRequest request)
    {
        logger.logInfo("RES_ACTIVE");
        String username = getUsernameFromRequest(request);

        if (username == null) {
            logger.logError("RES_ACTIVE_USRERR; IP: " + request.getRemoteAddr());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        SuccessReservationResponse response = reservationClient.successReservation(id, username);

        String responseInfo = response.getResponseInfo();

        if (responseInfo.equals("success")) {
            reservationService.setSuccessful(id);
        }
        logger.logInfo("RES_ACTIVE_SUCCESS");
        return new ResponseEntity<>(responseInfo,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/messages", method = RequestMethod.GET)
    public ResponseEntity<?> getReservationMessages(@PathVariable Long id, HttpServletRequest request)
    {

        String username = getUsernameFromRequest(request);

        if (username == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        GetMessagesResponse response = reservationClient.getMessages(id, username);

        reservationService.updateMessages(response.getMessage());

        ChatDTO chat = reservationService.getChat(id, username);


        return new ResponseEntity<>(chat, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}/messages", method = RequestMethod.POST)
    public ResponseEntity<?> postMessage(@PathVariable Long id, @RequestBody MessageDTO messageDTO,
                                                     HttpServletRequest request)
    {

        String username = getUsernameFromRequest(request);

        if (username == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

//        Message m = reservationService.postMesage(messageDTO, username);

        Message m = reservationService.formMessageForSOAP(messageDTO, username);

        PostMessageResponse response = reservationClient.postMessage(m, username);

        reservationService.updateMessages(response.getMessage());



        return new ResponseEntity<>(reservationService.getChat(id, username),
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
