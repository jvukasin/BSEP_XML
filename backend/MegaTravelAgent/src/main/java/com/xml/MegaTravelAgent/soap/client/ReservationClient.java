package com.xml.MegaTravelAgent.soap.client;

import com.xml.MegaTravelAgent.model.Reservation;
import com.xml.MegaTravelAgent.soap.reqres.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class ReservationClient extends WebServiceGatewaySupport implements IReservationClient {

    private static final String ENDPOINT_URI = "http://localhost:8084/reservation-service/ws";


    @Override
    public FetchReservationsResponse fetchAgentsReservations(String agentUsername) {
        System.out.println("fetchAgentsReservations client");

        FetchReservationsRequest request = new FetchReservationsRequest();
        request.setAgentUsername(agentUsername);

        FetchReservationsResponse response = (FetchReservationsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT_URI, request);

        return response;
    }

    @Override
    public PostReservationResponse postReservation(Reservation reservation, String agentUsername) {
        System.out.println("postReservations client");

        return null;
    }

    @Override
    public SuccessReservationResponse successReservation(Long reservationId, String agentUsername) {


        SuccessReservationRequest request = new SuccessReservationRequest();
        request.setAgentUsername(agentUsername);
        request.setReservationId(reservationId);

        SuccessReservationResponse response = (SuccessReservationResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT_URI, request);

        return response;
    }

    @Override
    public GetMessagesResponse getMessages(Long reservationId, String agentUsername) {
        System.out.println("getMessages client");


        return null;
    }
}
