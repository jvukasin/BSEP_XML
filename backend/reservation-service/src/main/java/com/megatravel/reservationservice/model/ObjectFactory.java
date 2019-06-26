
package com.megatravel.reservationservice.model;

import com.megatravel.reservationservice.soap.reqres.*;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.megatravel.reservationservice.model package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.megatravel.reservationservice.model
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
     * Create an instance of {@link GetMessagesResponse }
     * 
     */
    public GetMessagesResponse createGetMessagesResponse() {
        return new GetMessagesResponse();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link Reservation }
     * 
     */
    public Reservation createReservation() {
        return new Reservation();
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
     * Create an instance of {@link GetMessagesRequest }
     * 
     */
    public GetMessagesRequest createGetMessagesRequest() {
        return new GetMessagesRequest();
    }

    /**
     * Create an instance of {@link FetchReservationsRequest }
     * 
     */
    public FetchReservationsRequest createFetchReservationsRequest() {
        return new FetchReservationsRequest();
    }

    /**
     * Create an instance of {@link FetchReservationsResponse }
     * 
     */
    public FetchReservationsResponse createFetchReservationsResponse() {
        return new FetchReservationsResponse();
    }

    /**
     * Create an instance of {@link SuccessReservationResponse }
     * 
     */
    public SuccessReservationResponse createSuccessReservationResponse() {
        return new SuccessReservationResponse();
    }

    /**
     * Create an instance of {@link SuccessReservationRequest }
     * 
     */
    public SuccessReservationRequest createSuccessReservationRequest() {
        return new SuccessReservationRequest();
    }

    /**
     * Create an instance of {@link PostReservationResponse }
     * 
     */
    public PostReservationResponse createPostReservationResponse() {
        return new PostReservationResponse();
    }

    /**
     * Create an instance of {@link PostReservationRequest }
     * 
     */
    public PostReservationRequest createPostReservationRequest() {
        return new PostReservationRequest();
    }

    /**
     * Create an instance of {@link Comment }
     * 
     */
    public Comment createComment() {
        return new Comment();
    }

    /**
     * Create an instance of {@link Rating }
     * 
     */
    public Rating createRating() {
        return new Rating();
    }

    /**
     * Create an instance of {@link PostMessageRequest }
     *
     */
    public PostMessageRequest createPostMessageRequest() {
        return new PostMessageRequest();
    }

    /**
     * Create an instance of {@link PostMessageResponse }
     *
     */
    public PostMessageResponse createPostMessageResponse() {
        return new PostMessageResponse();
    }

}
