
package com.megatravel.accommodationservice.model;

import com.megatravel.accommodationservice.soap.reqres.*;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.megatravel.accommodationservice.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.megatravel.accommodationservice.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Agent }
     *
     */
    public Agent createAgent() {
        return new Agent();
    }


    /**
     * Create an instance of {@link EditAccommodationUnitRequest }
     * 
     */
    public EditAccommodationUnitRequest createEditAccommodationUnitRequest() {
        return new EditAccommodationUnitRequest();
    }

    /**
     * Create an instance of {@link AccommodationUnit }
     * 
     */
    public AccommodationUnit createAccommodationUnit() {
        return new AccommodationUnit();
    }

    /**
     * Create an instance of {@link Amenity }
     * 
     */
    public Amenity createAmenity() {
        return new Amenity();
    }

    /**
     * Create an instance of {@link Image }
     * 
     */
    public Image createImage() {
        return new Image();
    }

    /**
     * Create an instance of {@link SpecificPrice }
     * 
     */
    public SpecificPrice createSpecificPrice() {
        return new SpecificPrice();
    }

    /**
     * Create an instance of {@link Location }
     * 
     */
    public Location createLocation() {
        return new Location();
    }

    /**
     * Create an instance of {@link City }
     * 
     */
    public City createCity() {
        return new City();
    }

    /**
     * Create an instance of {@link Country }
     * 
     */
    public Country createCountry() {
        return new Country();
    }

    /**
     * Create an instance of {@link EditAccommodationUnitResponse }
     * 
     */
    public EditAccommodationUnitResponse createEditAccommodationUnitResponse() {
        return new EditAccommodationUnitResponse();
    }

    /**
     * Create an instance of {@link GetAccommodationUnitRequest }
     * 
     */
    public GetAccommodationUnitRequest createGetAccommodationUnitRequest() {
        return new GetAccommodationUnitRequest();
    }

    /**
     * Create an instance of {@link GetAccommodationUnitResponse }
     * 
     */
    public GetAccommodationUnitResponse createGetAccommodationUnitResponse() {
        return new GetAccommodationUnitResponse();
    }

    /**
     * Create an instance of {@link DeleteAccommodationUnitRequest }
     * 
     */
    public DeleteAccommodationUnitRequest createDeleteAccommodationUnitRequest() {
        return new DeleteAccommodationUnitRequest();
    }

    /**
     * Create an instance of {@link DeleteAccommodationUnitResponse }
     * 
     */
    public DeleteAccommodationUnitResponse createDeleteAccommodationUnitResponse() {
        return new DeleteAccommodationUnitResponse();
    }

    /**
     * Create an instance of {@link PostAccommodationUnitRequest }
     * 
     */
    public PostAccommodationUnitRequest createPostAccommodationUnitRequest() {
        return new PostAccommodationUnitRequest();
    }

    /**
     * Create an instance of {@link PostAccommodationUnitResponse }
     * 
     */
    public PostAccommodationUnitResponse createPostAccommodationUnitResponse() {
        return new PostAccommodationUnitResponse();
    }


    /**
     * Create an instance of {@link GetAccommodationSettingsResponse }
     *
     */
    public GetAccommodationSettingsResponse createGetAccommodationSettingsResponse() {
        return new GetAccommodationSettingsResponse();
    }

    /**
     * Create an instance of {@link GetAccommodationSettingsRequest }
     *
     */
    public GetAccommodationSettingsRequest createGetAccommodationSettingsRequest() {
        return new GetAccommodationSettingsRequest();
    }

    /**
     * Create an instance of {@link Rating }
     * 
     */
    public Rating createRating() {
        return new Rating();
    }

    /**
     * Create an instance of {@link Reservation }
     * 
     */
    public Reservation createReservation() {
        return new Reservation();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link GetAURatingsRequest }
     *
     */
    public GetAURatingsRequest createGetAURatingsRequest() {
        return new GetAURatingsRequest();
    }


    /**
     * Create an instance of {@link GetAURatingsResponse }
     *
     */
    public GetAURatingsResponse createGetAURatingsResponse() {
        return new GetAURatingsResponse();
    }

}
