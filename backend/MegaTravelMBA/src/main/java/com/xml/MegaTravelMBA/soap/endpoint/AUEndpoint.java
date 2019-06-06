package com.xml.MegaTravelMBA.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.xml.MegaTravelMBA.model.AccommodationUnitRequest;
import com.xml.MegaTravelMBA.model.AccommodationUnitResponse;
import com.xml.MegaTravelMBA.repository.AccommodationUnitRepository;

@Endpoint
public class AUEndpoint {

    private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/MegaTravel/soap";
	
	@Autowired 
	private AccommodationUnitRepository auRepo;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "AccommodationUnitRequest")
    @ResponsePayload
    public AccommodationUnitResponse createAccomodationUnit(@RequestPayload AccommodationUnitRequest request) {
		AccommodationUnitResponse response = new AccommodationUnitResponse();
        response.setAccommodationUnit(auRepo.findOneById(request.getAccommodationUnit().getId()));
 
        return response;
    }
	
}
