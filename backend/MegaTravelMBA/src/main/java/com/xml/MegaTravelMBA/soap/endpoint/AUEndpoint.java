package com.xml.MegaTravelMBA.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.xml.MegaTravelMBA.repository.AccommodationUnitRepository;
import com.xml.MegaTravelMBA.soap.reqres.AccommodationUnitRequest;
import com.xml.MegaTravelMBA.soap.reqres.AccommodationUnitResponse;
import com.xml.MegaTravelMBA.soap.reqres.GetAccommodationUnitRequest;
import com.xml.MegaTravelMBA.soap.reqres.GetAccommodationUnitResponse;

@Endpoint
public class AUEndpoint {

    private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/MegaTravel/soap";
	
	@Autowired 
	private AccommodationUnitRepository auRepo;
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAccommodationUnitRequest")
    @ResponsePayload
    public GetAccommodationUnitResponse createAccomodationUnit(@RequestPayload GetAccommodationUnitRequest request) {
		System.out.println("Hit endpoint GetAccommodationUnit");
		GetAccommodationUnitResponse response = new GetAccommodationUnitResponse();
		response.setAccommodationUnit(auRepo.findOneById(request.getId()));
		
		/*
		 * AccommodationUnit au = new AccommodationUnit(); au.setName("Lepi vule");
		 * au.setId(new Long(1)); au.setDescription("Neka deskripcija glupava");
		 * au.setPrice(300); response.setAccommodationUnit(au);
		 */
		System.out.println(response.getAccommodationUnit().getName());
        
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "AccommodationUnitRequest")
    @ResponsePayload
    public AccommodationUnitResponse createAccomodationUnit(@RequestPayload AccommodationUnitRequest request) {
		AccommodationUnitResponse response = new AccommodationUnitResponse();
        //response.setAccommodationUnit(auRepo.findOneById(request.getAccommodationUnit().getId()));
 
        return response;
    }
	
}
