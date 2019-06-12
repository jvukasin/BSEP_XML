package com.megatravel.reservationservice.soap.endpoints;

import com.megatravel.reservationservice.soap.reqres.*;

public interface IReservationEndpoint {

    FetchReservationsResponse fetchAgentsReservations(FetchReservationsRequest request);

    PostReservationResponse postReservation(PostReservationRequest request);

    SuccessReservationResponse successReservation(SuccessReservationRequest request);

    GetMessagesResponse getMessages(GetMessagesRequest request);
}
