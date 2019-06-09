package com.xml.MegaTravelMBA.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.xml.MegaTravelMBA.model.AccommodationUnit;
import com.xml.MegaTravelMBA.model.Amenity;
import com.xml.MegaTravelMBA.model.Image;
import com.xml.MegaTravelMBA.model.ObjectFactory;
import com.xml.MegaTravelMBA.model.SpecificPrice;
import com.xml.MegaTravelMBA.repository.AccommodationUnitRepository;
import com.xml.MegaTravelMBA.soap.reqres.GetAccommodationUnitRequest;
import com.xml.MegaTravelMBA.soap.reqres.GetAccommodationUnitResponse;
import com.xml.MegaTravelMBA.soap.reqres.PostAccommodationUnitRequest;
import com.xml.MegaTravelMBA.soap.reqres.PostAccommodationUnitResponse;

@Endpoint
public class AUEndpoint {

    private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/MegaTravel/soap";
	
	@Autowired 
	private AccommodationUnitRepository auRepo;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAccommodationUnitRequest")
    @ResponsePayload
    public GetAccommodationUnitResponse getAccommodationUnit(@RequestPayload GetAccommodationUnitRequest request) {
		
		AccommodationUnit au = auRepo.findById(request.getId()).get();
		
		GetAccommodationUnitResponse response = new GetAccommodationUnitResponse();
		response.setAccommodationUnit(au);
	
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "PostAccommodationUnitRequest")
    @ResponsePayload
    public PostAccommodationUnitResponse createAccomodationUnit(@RequestPayload PostAccommodationUnitRequest request) {
		
		AccommodationUnit requestAu = request.getAccommodationUnit();
		
		// Insertion in DB
		AccommodationUnit au = new AccommodationUnit();
		au = auRepo.save(requestAu);

		PostAccommodationUnitResponse response = new PostAccommodationUnitResponse();
		response.setAccommodationUnit(au);
 
        return response;
    }
	
}
