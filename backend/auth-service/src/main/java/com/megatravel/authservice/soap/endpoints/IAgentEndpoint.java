package com.megatravel.authservice.soap.endpoints;


import com.megatravel.authservice.soap.reqres.FetchAgentsRequest;
import com.megatravel.authservice.soap.reqres.FetchAgentsResponse;

public interface IAgentEndpoint {

   FetchAgentsResponse fetchAgents(FetchAgentsRequest request);



}
