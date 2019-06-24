package com.xml.MegaTravelAgent.soap.client;

import com.xml.MegaTravelAgent.soap.reqres.FetchAgentsResponse;
import com.xml.MegaTravelAgent.soap.reqres.FetchReservationsResponse;

public interface IAuthClient {

    FetchAgentsResponse fetchAgents();
}
