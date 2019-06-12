package com.megatravel.accommodationservice.soap.endpoints;

import com.megatravel.accommodationservice.model.*;
import com.megatravel.accommodationservice.soap.reqres.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class AccommodationUnitEndpoint implements IAccommodationUnitEndpoint {
    private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/MegaTravel/soap_accommodation_unit";

    //@Autowired
    //private AccommodationUnitRepository auRepo;

    @Autowired
    private ObjectFactory factory;


    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAccommodationUnitRequest")
    @ResponsePayload
    public GetAccommodationUnitResponse getAccommodationUnit(@RequestPayload GetAccommodationUnitRequest request) {


        System.out.println("Hit getAccommodationUnit");

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
    public PostAccommodationUnitResponse createAccommodationUnit(@RequestPayload PostAccommodationUnitRequest request) {

        System.out.println("Hit postAccommodationUnit");

        AccommodationUnit requestAu = request.getAccommodationUnit();

        // Insertion in DB
        AccommodationUnit au = new AccommodationUnit();
        //au = auRepo.save(requestAu);

        PostAccommodationUnitResponse response = new PostAccommodationUnitResponse();
        response.setAccommodationUnit(au);

        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "EditAccommodationUnitRequest")
    @ResponsePayload
    public EditAccommodationUnitResponse editAccommodationUnit(EditAccommodationUnitRequest request) {

        System.out.println("Hit editAccommodationUnit");

        EditAccommodationUnitResponse response = factory.createEditAccommodationUnitResponse();
        AccommodationUnit au = factory.createAccommodationUnit();

        au.setName("EditAccommodation");

        response.setAccommodationUnit(au);

        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteAccommodationUnitRequest")
    @ResponsePayload
    public DeleteAccommodationUnitResponse deleteAccommodationUnit(DeleteAccommodationUnitRequest request) {

        System.out.println("Hit deleteAccommodationUnit");

        DeleteAccommodationUnitResponse response = factory.createDeleteAccommodationUnitResponse();
        response.setResponseInfo("deleted");

        return response;
    }
}
