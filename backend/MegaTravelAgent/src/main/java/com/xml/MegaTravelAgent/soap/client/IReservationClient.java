package com.xml.MegaTravelAgent.soap.client;

import com.xml.MegaTravelAgent.model.Reservation;
import com.xml.MegaTravelAgent.soap.reqres.*;

public interface IReservationClient {

    FetchReservationsResponse fetchAgentsReservations(Long agentId);

    PostReservationResponse postReservation(Reservation reservation, Long agentId);

    SuccessReservationResponse successReservation(Long reservationId, Long agentId);

    GetMessagesResponse getMessages(Long reservationId, Long agentId);
}
