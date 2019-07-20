package com.xml.MegaTravelAgent.soap.client;

import com.xml.MegaTravelAgent.soap.reqres.FetchAgentsRequest;
import com.xml.MegaTravelAgent.soap.reqres.FetchAgentsResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class AuthClient extends WebServiceGatewaySupport implements IAuthClient {

    private static final String ENDPOINT_URI = "https://192.168.43.135:8440/auth-service/ws";

    @Override
    public FetchAgentsResponse fetchAgents() {

        FetchAgentsRequest request = new FetchAgentsRequest();

        request.setRequestMessage("Fetch new agents");

        FetchAgentsResponse response = (FetchAgentsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT_URI, request);

        return response;
    }
}
