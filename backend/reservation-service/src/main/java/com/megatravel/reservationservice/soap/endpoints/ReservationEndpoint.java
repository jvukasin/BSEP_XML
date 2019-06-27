package com.megatravel.reservationservice.soap.endpoints;

import com.megatravel.reservationservice.model.*;
import com.megatravel.reservationservice.services.ReservationService;
import com.megatravel.reservationservice.soap.reqres.*;
import exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class ReservationEndpoint implements IReservationEndpoint {

    private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/MegaTravel/soap_reservation";


    @Autowired
    private ObjectFactory factory;

    @Autowired
    private ReservationService reservationService;

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "FetchReservationsRequest")
    @ResponsePayload
    public FetchReservationsResponse fetchAgentsReservations(@RequestPayload FetchReservationsRequest request) {

        // Kada se poveze Reservation model sa bazom (dodaju anotacije) samo ce se uzeti rezervacije od agenta i ubaciti u response
        System.out.println("Agent username: " + request.getAgentUsername());

        List<Reservation> reservations = reservationService.getAgentReservations(request.getAgentUsername());

        FetchReservationsResponse response = factory.createFetchReservationsResponse();

        for (Reservation r: reservations) {
            AccommodationUnit au = new AccommodationUnit();
            au.setId(r.getAccommodationUnit().getId());
            au.setName(r.getAccommodationUnit().getName());
            r.setAccommodationUnit(au);

            if (r.getReservator() != null) {
                r.setUsernameReservator(r.getReservator().getUsername());
            } else {
                r.setUsernameReservator(request.getAgentUsername());
            }

            response.getReservation().add((r));
        }

        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PostReservationRequest")
    @ResponsePayload
    public PostReservationResponse postReservation(@RequestPayload PostReservationRequest request) {

        // Ovde ce morati da se proveri da li je u medju vremenu neko iz glavne aplikacije od korisnika rezervisao
        // taj smestaj u tom periodu, u slucaju da jeste, u responsu se vraca null za rezervaciju (ili smisliti neki bolji mehanizam)

        Reservation reservation = request.getReservation();

        reservation = reservationService.createFromSOAP(reservation);

        PostReservationResponse response = factory.createPostReservationResponse();
        response.setReservation(reservation);

        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SuccessReservationRequest")
    @ResponsePayload
    public SuccessReservationResponse successReservation(@RequestPayload SuccessReservationRequest request) {

        System.out.println(request.getReservationId());


        SuccessReservationResponse response = factory.createSuccessReservationResponse();

        try {
            reservationService.setSuccessful(request.getReservationId());
            response.setResponseInfo("success");
        } catch (BusinessException e) {
            response.setResponseInfo(e.getMessage());
        }


        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PostMessageRequest")
    @ResponsePayload
    public PostMessageResponse postMessage(@RequestPayload PostMessageRequest request) {

        System.out.println("Hit postMessage endpoint");

        reservationService.postMessageFromSOAP(request.getMessage(), request.getAgentUsername());

        PostMessageResponse response = factory.createPostMessageResponse();

        for (Message m: reservationService.getAllMessagesByReservationId(request.getMessage().getIdReservation())) {
            response.getMessage().add(m);
        }

        return response;

    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetMessagesRequest")
    @ResponsePayload
    public GetMessagesResponse getMessages(@RequestPayload GetMessagesRequest request) {

        System.out.println("Hit getMessages endpoint");

        GetMessagesResponse response = factory.createGetMessagesResponse();

        for (Message m: reservationService.getAllMessagesByReservationId(request.getReservationId())) {
            response.getMessage().add(m);
        }

        return response;
    }
}
