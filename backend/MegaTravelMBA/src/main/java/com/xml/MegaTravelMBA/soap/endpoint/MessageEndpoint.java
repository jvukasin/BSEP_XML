package com.xml.MegaTravelMBA.soap.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.xml.MegaTravelMBA.soap.reqres.MessageRequest;
import com.xml.MegaTravelMBA.soap.reqres.MessageResponse;

@Endpoint
public class MessageEndpoint {
	
private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/MegaTravel/soap";
    
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "MessageRequest")
    @ResponsePayload
    public MessageResponse getMessages(@RequestPayload MessageRequest request) {
    	
    	MessageResponse response = new MessageResponse();
//    	response.setMessage();
		
        return response;
    }
}
