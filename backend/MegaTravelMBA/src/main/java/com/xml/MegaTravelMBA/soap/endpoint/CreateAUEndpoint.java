package com.xml.MegaTravelMBA.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.xml.MegaTravelMBA.repository.AccommodationUnitRepository;

public class CreateAUEndpoint {
	

    private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit";
	
	@Autowired 
	private AccommodationUnitRepository auRepo;
	
//	  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createAURequest")
//    @ResponsePayload
//    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
//        GetCountryResponse response = new GetCountryResponse();
//        response.setCountry(countryRepository.findCountry(request.getName()));
// 
//        return response;
//    }
	
}
