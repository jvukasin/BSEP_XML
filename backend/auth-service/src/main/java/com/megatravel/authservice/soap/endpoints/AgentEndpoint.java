package com.megatravel.authservice.soap.endpoints;

import com.megatravel.authservice.service.TPersonService;
import com.megatravel.authservice.soap.reqres.AgentSOAP;
import com.megatravel.authservice.soap.reqres.FetchAgentsRequest;
import com.megatravel.authservice.soap.reqres.FetchAgentsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;


@Endpoint
public class AgentEndpoint implements IAgentEndpoint {
    private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/MegaTravel/soap_agent";

    @Autowired
    private TPersonService tPersonService;


    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "FetchAgentsRequest")
    @ResponsePayload
    public FetchAgentsResponse fetchAgents(@RequestPayload FetchAgentsRequest request) {

        List<AgentSOAP> agents = tPersonService.findAllAgentsSOAP();


        FetchAgentsResponse response = new FetchAgentsResponse();

        for (AgentSOAP a: agents) {
            response.getAgentSOAP().add(a);
        }


        return response;


    }


}
