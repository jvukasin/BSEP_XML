
package com.megatravel.accommodationservice.soap.reqres;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="agentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "accommodationUnitId",
    "agentId"
})
@XmlRootElement(name = "DeleteAccommodationUnitRequest", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_accommodation_unit")
public class DeleteAccommodationUnitRequest {

    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_accommodation_unit")
    protected long accommodationUnitId;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_accommodation_unit")
    protected long agentId;

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

    /**
     * Gets the value of the agentId property.
     * 
     */
    public long getAgentId() {
        return agentId;
    }

    /**
     * Sets the value of the agentId property.
     * 
     */
    public void setAgentId(long value) {
        this.agentId = value;
    }

}
