
package com.megatravel.reservationservice.soap.reqres;

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
 *         &lt;element name="reservationId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="agentUsername" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "reservationId",
    "agentUsername"
})
@XmlRootElement(name = "SuccessReservationRequest", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_reservation")
public class SuccessReservationRequest {

    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_reservation")
    protected long reservationId;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_reservation", required = true)
    protected String agentUsername;

    /**
     * Gets the value of the reservationId property.
     * 
     */
    public long getReservationId() {
        return reservationId;
    }

    /**
     * Sets the value of the reservationId property.
     * 
     */
    public void setReservationId(long value) {
        this.reservationId = value;
    }

    /**
     * Gets the value of the agentUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentUsername() {
        return agentUsername;
    }

    /**
     * Sets the value of the agentUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentUsername(String value) {
        this.agentUsername = value;
    }

}
