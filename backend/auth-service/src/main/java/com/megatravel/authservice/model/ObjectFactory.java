
package com.megatravel.authservice.model;

import com.megatravel.authservice.soap.reqres.FetchAgentsRequest;
import com.megatravel.authservice.soap.reqres.FetchAgentsResponse;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.megatravel.authservice.model package. 
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

    private final static QName _Admin_QNAME = new QName("http://www.ftn.uns.ac.rs/MegaTravel/users", "Admin");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.megatravel.authservice.model
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
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Reservation }
     * 
     */
    public Reservation createReservation() {
        return new Reservation();
    }

    /**
     * Create an instance of {@link Rating }
     * 
     */
    public Rating createRating() {
        return new Rating();
    }

    /**
     * Create an instance of {@link FetchAgentsRequest }
     *
     */
    public FetchAgentsRequest createFetchAgentsRequest() {
        return new FetchAgentsRequest();
    }

    /**
     * Create an instance of {@link FetchAgentsResponse }
     *
     */
    public FetchAgentsResponse createFetchAgentsResponse() {
        return new FetchAgentsResponse();
    }
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/users", name = "Admin")
    public JAXBElement<TPerson> createAdmin(TPerson value) {
        return new JAXBElement<TPerson>(_Admin_QNAME, TPerson.class, null, value);
    }

}
