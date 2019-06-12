package com.megatravel.accommodationservice.soap.endpoints;

import com.megatravel.accommodationservice.model.AccommodationUnit;
import com.megatravel.accommodationservice.soap.reqres.GetAccommodationUnitRequest;
import com.megatravel.accommodationservice.soap.reqres.GetAccommodationUnitResponse;
import com.megatravel.accommodationservice.soap.reqres.PostAccommodationUnitRequest;
import com.megatravel.accommodationservice.soap.reqres.PostAccommodationUnitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class AccommodationUnitEndpoint implements IAccommodationUnitEndpoint {
    private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/MegaTravel/soap";

    //@Autowired
    //private AccommodationUnitRepository auRepo;

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAccommodationUnitRequest")
    @ResponsePayload
    public GetAccommodationUnitResponse getAccommodationUnit(@RequestPayload GetAccommodationUnitRequest request) {

        //AccommodationUnit au = auRepo.findById(request.getId()).get();

        // temp:
        AccommodationUnit au = new AccommodationUnit();
        au.setName("Blejaz");

        GetAccommodationUnitResponse response = new GetAccommodationUnitResponse();
        response.setAccommodationUnit(au);

        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PostAccommodationUnitRequest")
    @ResponsePayload
    public PostAccommodationUnitResponse createAccomodationUnit(@RequestPayload PostAccommodationUnitRequest request) {

        AccommodationUnit requestAu = request.getAccommodationUnit();

        // Insertion in DB
        AccommodationUnit au = new AccommodationUnit();
        //au = auRepo.save(requestAu);

        PostAccommodationUnitResponse response = new PostAccommodationUnitResponse();
        response.setAccommodationUnit(au);

        return response;
    }
}
