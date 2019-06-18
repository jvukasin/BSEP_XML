package com.megatravel.accommodationservice.soap.endpoints;

import com.megatravel.accommodationservice.model.*;
import com.megatravel.accommodationservice.service.AccommodationUnitService;
import com.megatravel.accommodationservice.soap.reqres.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Endpoint
public class AccommodationUnitEndpoint implements IAccommodationUnitEndpoint {
    private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/MegaTravel/soap_accommodation_unit";

    //@Autowired
    //private AccommodationUnitRepository auRepo;

    @Autowired
    private ObjectFactory factory;

    @Autowired
    AccommodationUnitService acService;


    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAccommodationUnitRequest")
    @ResponsePayload
    public GetAccommodationUnitResponse getAccommodationUnit(@RequestPayload GetAccommodationUnitRequest request) {


        System.out.println("Hit getAccommodationUnit");

        //AccommodationUnit au = auRepo.findById(request.getId()).get();

        // temp:
        AccommodationUnit au = new AccommodationUnit();
        Amenity amenity = new Amenity();
        amenity.setFaIcon("fa fa-wifi");
        amenity.setName("WiFi");
        amenity.setId(new Long(12));

        Set<Amenity> amenities = new HashSet<Amenity>();
        amenities.add(amenity);


        au.setName("Blejaz");
        au.setAmenity(amenities);
        GetAccommodationUnitResponse response = new GetAccommodationUnitResponse();
        response.setAccommodationUnit(au);

        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PostAccommodationUnitRequest")
    @ResponsePayload
    public PostAccommodationUnitResponse createAccommodationUnit(@RequestPayload PostAccommodationUnitRequest request) {

        System.out.println("Hit postAccommodationUnit");

        AccommodationUnit au = request.getAccommodationUnit();
        acService.saveFromSoap(au);

        PostAccommodationUnitResponse response = new PostAccommodationUnitResponse();
        response.setAccommodationUnit(au);

        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "EditAccommodationUnitRequest")
    @ResponsePayload
    public EditAccommodationUnitResponse editAccommodationUnit(@RequestPayload EditAccommodationUnitRequest request) {

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
    public DeleteAccommodationUnitResponse deleteAccommodationUnit(@RequestPayload DeleteAccommodationUnitRequest request) {

        System.out.println("Hit deleteAccommodationUnit");

        DeleteAccommodationUnitResponse response = factory.createDeleteAccommodationUnitResponse();
        response.setResponseInfo("deleted");

        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAccommodationSettingsRequest")
    @ResponsePayload
    public GetAccommodationSettingsResponse getAccommodationSettingsResponse(@RequestPayload GetAccommodationSettingsRequest request) {

        System.out.println("Hit GetAccommodationSettingsResponse endpoint");

        GetAccommodationSettingsResponse response = factory.createGetAccommodationSettingsResponse();

        List<Amenity> amenities = acService.findAllAmenities();

        for (Amenity a: amenities) {
            response.getAmenity().add(a);
        }

        AccommodationType t1 = new AccommodationType();
        t1.setType("hotel");
        AccommodationType t2 = new AccommodationType();
        t2.setType("bnb");
        AccommodationType t3 = new AccommodationType();
        t3.setType("studio");
        AccommodationType t4 = new AccommodationType();
        t4.setType("entire house");

        response.getAccommodationType().add(t1);
        response.getAccommodationType().add(t2);
        response.getAccommodationType().add(t3);
        response.getAccommodationType().add(t4);


        return response;
    }
}
