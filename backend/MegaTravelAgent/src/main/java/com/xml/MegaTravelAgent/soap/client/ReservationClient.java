package com.xml.MegaTravelAgent.soap.client;

import com.xml.MegaTravelAgent.model.Reservation;
import com.xml.MegaTravelAgent.soap.reqres.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class ReservationClient extends WebServiceGatewaySupport implements IReservationClient {

    private static final String ENDPOINT_URI = "http://localhost:8084/reservation-service/ws";


    @Override
    public FetchReservationsResponse fetchAgentsReservations(Long agentId) {
        System.out.println("fetchAgentsReservations client");

        FetchReservationsRequest request = new FetchReservationsRequest();
        request.setAgentId(agentId);

        FetchReservationsResponse response = (FetchReservationsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT_URI, request);

        return response;
    }

    @Override
    public PostReservationResponse postReservation(Reservation reservation, Long agentId) {
        System.out.println("postReservations client");

        return null;
    }

    @Override
    public SuccessReservationResponse successReservation(Long reservationId, Long agentId) {
        System.out.println("successReservation client");

        return null;
    }

    @Override
    public GetMessagesResponse getMessages(Long reservationId, Long agentId) {
        System.out.println("getMessages client");


        return null;
    }
}
