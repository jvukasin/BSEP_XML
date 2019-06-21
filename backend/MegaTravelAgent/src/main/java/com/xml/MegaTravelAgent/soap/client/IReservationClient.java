package com.xml.MegaTravelAgent.soap.client;

import com.xml.MegaTravelAgent.model.Reservation;
import com.xml.MegaTravelAgent.soap.reqres.*;

public interface IReservationClient {

    FetchReservationsResponse fetchAgentsReservations(String agentUsername);

    PostReservationResponse postReservation(Reservation reservation, String agentUsername);

    SuccessReservationResponse successReservation(Long reservationId, String agentUsername);

    GetMessagesResponse getMessages(Long reservationId, String agentUsername);
}
