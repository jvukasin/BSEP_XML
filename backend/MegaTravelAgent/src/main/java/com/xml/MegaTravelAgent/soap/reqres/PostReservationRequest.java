
package com.xml.MegaTravelAgent.soap.reqres;

import com.xml.MegaTravelAgent.model.Reservation;

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
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/MegaTravel/reservation}Reservation"/>
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
    "reservation",
    "agentUsername"
})
@XmlRootElement(name = "PostReservationRequest", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_reservation")
public class PostReservationRequest {

    @XmlElement(name = "Reservation", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/reservation", required = true)
    protected Reservation reservation;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_reservation", required = true)
    protected String agentUsername;

    /**
     * Gets the value of the reservation property.
     * 
     * @return
     *     possible object is
     *     {@link Reservation }
     *     
     */
    public Reservation getReservation() {
        return reservation;
    }

    /**
     * Sets the value of the reservation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reservation }
     *     
     */
    public void setReservation(Reservation value) {
        this.reservation = value;
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
