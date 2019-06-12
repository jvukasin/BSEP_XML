package com.megatravel.reservationservice.soap.endpoints;

import com.megatravel.reservationservice.soap.reqres.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ReservationEndpoint implements IReservationEndpoint {

    private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/MegaTravel/soap";


    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "FetchReservationsRequest")
    @ResponsePayload
    public FetchReservationsResponse fetchAgentsReservations(@RequestPayload FetchReservationsRequest request) {

        // Kada se poveze Reservation model sa bazom (dodaju anotacije) samo ce se uzeti rezervacije od agenta i ubaciti u response
        System.out.println("Agent id: " + request.getAgentId());

        // Test
        Reservation r1 = new Reservation();
        r1.setPrice(300);
        r1.setId(new Long(1));

        Reservation r2 = new Reservation();
        r2.setPrice(450);
        r2.setId(new Long(2));

        FetchReservationsResponse response = new FetchReservationsResponse();
        response.getReservation().add((r1));
        response.getReservation().add((r2));

        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PostReservationRequest")
    @ResponsePayload
    public PostReservationResponse postReservation(@RequestPayload PostReservationRequest request) {

        // Ovde ce morati da se proveri da li je u medju vremenu neko iz glavne aplikacije od korisnika rezervisao
        // taj smestaj u tom periodu, u slucaju da jeste, u responsu se vraca null za rezervaciju (ili smisliti neki bolji mehanizam)

        System.out.println("AgentID: " + request.getAgentId());
        System.out.println("Reservation: " + request.getReservation());

        PostReservationResponse response = new PostReservationResponse();
        response.setReservation(null);

        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SuccessReservationRequest")
    @ResponsePayload
    public SuccessReservationResponse successReservation(@RequestPayload SuccessReservationRequest request) {

        System.out.println(request.getReservationId());

        // ovde se setuje rezervacija na success

        SuccessReservationResponse response = new SuccessReservationResponse();
        response.setResponseInfo("success");

        return response;
    }
}
