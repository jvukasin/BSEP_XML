
package com.megatravel.accommodationservice.soap.reqres;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="accommodationUnitId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "accommodationUnitId"
})
@XmlRootElement(name = "GetAURatingsRequest", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_accommodation_unit")
public class GetAURatingsRequest {

    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_accommodation_unit")
    protected long accommodationUnitId;

    /**
     * Gets the value of the accommodationUnitId property.
     * 
     */
    public long getAccommodationUnitId() {
        return accommodationUnitId;
    }

    /**
     * Sets the value of the accommodationUnitId property.
     * 
     */
    public void setAccommodationUnitId(long value) {
        this.accommodationUnitId = value;
    }

}
