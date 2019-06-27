package com.xml.MegaTravelAgent.soap.reqres;

import com.xml.MegaTravelAgent.model.Message;
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
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/MegaTravel/Message}Message"/>
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
    "message",
    "agentUsername"
})
@XmlRootElement(name = "PostMessageRequest", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_reservation")
public class PostMessageRequest {

    @XmlElement(name = "Message", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message", required = true)
    protected Message message;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_reservation", required = true)
    protected String agentUsername;

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link Message }
     *     
     */
    public Message getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link Message }
     *     
     */
    public void setMessage(Message value) {
        this.message = value;
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
