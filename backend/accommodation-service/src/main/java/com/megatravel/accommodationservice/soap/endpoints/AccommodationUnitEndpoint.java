package com.megatravel.accommodationservice.soap.endpoints;

import com.megatravel.accommodationservice.dto.RatingAverageDTO;
import com.megatravel.accommodationservice.dto.RatingDTO;
import com.megatravel.accommodationservice.model.*;
import com.megatravel.accommodationservice.service.AccommodationUnitService;
import com.megatravel.accommodationservice.soap.reqres.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.*;


@Endpoint
public class AccommodationUnitEndpoint implements IAccommodationUnitEndpoint {
    private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/MegaTravel/soap_accommodation_unit";


    @Autowired
    private ObjectFactory factory;

    @Autowired
    AccommodationUnitService acService;

    @Autowired
    RestTemplate template;



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
        String agentUsername = request.getAgentUsername();
        acService.saveFromSoap(au, agentUsername);

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
        Collection<AccommodationType> types = acService.findAllTypes();
        Collection<AccommodationCategory> categories = acService.findAllCategories();

        for (Amenity a: amenities) {
            response.getAmenity().add(a);
        }

        for (AccommodationType t: types) {
            response.getAccommodationType().add(t);
        }

        for (AccommodationCategory c: categories) {
            response.getAccommodationCategory().add(c);
        }


        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAURatingsRequest")
    @ResponsePayload
    public GetAURatingsResponse getAURatings(@RequestPayload GetAURatingsRequest request) {

        System.out.println("Hit GetAURatings endpoint");

        GetAURatingsResponse response = factory.createGetAURatingsResponse();
        List<RatingDTO> dtos = new ArrayList<RatingDTO>();

        ResponseEntity<List<RatingDTO>> responseCloudRatings = template.exchange(
                "http://localhost:8331/getAURatings?id="+request.getAccommodationUnitId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RatingDTO>>(){});
        dtos = responseCloudRatings.getBody();

        for (RatingDTO dto: dtos) {
            Rating r = acService.formRatingFromDTO(dto);
            response.getRating().add(r);
            System.out.println(r.getPostingDate() + " " + r.getComment());
        }


        ResponseEntity<List<RatingAverageDTO>> responseAvg = template.exchange(
                "http://localhost:8330/getRatingAverage?id="+request.getAccommodationUnitId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RatingAverageDTO>>(){});

        List<RatingAverageDTO> ratingAverageDTO = (List<RatingAverageDTO>) responseAvg.getBody();
        RatingAverageDTO retVal = ratingAverageDTO.iterator().next();

        response.setRatingAvg(retVal.getRatingAvg());
        System.out.println(retVal.getRatingAvg());

        return response;
    }
}
