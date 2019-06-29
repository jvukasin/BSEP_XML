package com.xml.MegaTravelAgent.soap.reqres;

import com.xml.MegaTravelAgent.model.AccommodationCategory;
import com.xml.MegaTravelAgent.model.AccommodationType;
import com.xml.MegaTravelAgent.model.Amenity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit}Amenity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/MegaTravel/soap_accommodation_unit}AccommodationType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/MegaTravel/soap_accommodation_unit}AccommodationCategory" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "amenity",
    "accommodationType",
    "accommodationCategory"
})
@XmlRootElement(name = "GetAccommodationSettingsResponse", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_accommodation_unit")
public class GetAccommodationSettingsResponse {

    @XmlElement(name = "Amenity", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit")
    protected List<Amenity> amenity;
    @XmlElement(name = "AccommodationType", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_accommodation_unit")
    protected List<AccommodationType> accommodationType;
    @XmlElement(name = "AccommodationCategory", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_accommodation_unit")
    protected List<AccommodationCategory> accommodationCategory;

    /**
     * Gets the value of the amenity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amenity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmenity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Amenity }
     * 
     * 
     */
    public List<Amenity> getAmenity() {
        if (amenity == null) {
            amenity = new ArrayList<Amenity>();
        }
        return this.amenity;
    }

    /**
     * Gets the value of the accommodationType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accommodationType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccommodationType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccommodationType }
     * 
     * 
     */
    public List<AccommodationType> getAccommodationType() {
        if (accommodationType == null) {
            accommodationType = new ArrayList<AccommodationType>();
        }
        return this.accommodationType;
    }

    /**
     * Gets the value of the accommodationCategory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accommodationCategory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccommodationCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccommodationCategory }
     * 
     * 
     */
    public List<AccommodationCategory> getAccommodationCategory() {
        if (accommodationCategory == null) {
            accommodationCategory = new ArrayList<AccommodationCategory>();
        }
        return this.accommodationCategory;
    }

}
