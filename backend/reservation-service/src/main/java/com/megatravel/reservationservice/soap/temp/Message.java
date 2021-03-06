
package com.megatravel.reservationservice.soap.temp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="idReservation" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="usernameSender" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usernameReceiver" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isAgentsMessage" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isUsersMessage" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idReservation",
    "content",
    "date",
    "usernameSender",
    "usernameReceiver",
    "isAgentsMessage",
    "isUsersMessage"
})
@XmlRootElement(name = "Message", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message")
public class Message {

    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message")
    protected long idReservation;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message", required = true)
    protected String content;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message", required = true)
    protected String usernameSender;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message", required = true)
    protected String usernameReceiver;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message")
    protected boolean isAgentsMessage;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message")
    protected boolean isUsersMessage;
    @XmlAttribute(name = "id")
    protected Long id;

    /**
     * Gets the value of the idReservation property.
     * 
     */
    public long getIdReservation() {
        return idReservation;
    }

    /**
     * Sets the value of the idReservation property.
     * 
     */
    public void setIdReservation(long value) {
        this.idReservation = value;
    }

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the usernameSender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsernameSender() {
        return usernameSender;
    }

    /**
     * Sets the value of the usernameSender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsernameSender(String value) {
        this.usernameSender = value;
    }

    /**
     * Gets the value of the usernameReceiver property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsernameReceiver() {
        return usernameReceiver;
    }

    /**
     * Sets the value of the usernameReceiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsernameReceiver(String value) {
        this.usernameReceiver = value;
    }

    /**
     * Gets the value of the isAgentsMessage property.
     * 
     */
    public boolean isIsAgentsMessage() {
        return isAgentsMessage;
    }

    /**
     * Sets the value of the isAgentsMessage property.
     * 
     */
    public void setIsAgentsMessage(boolean value) {
        this.isAgentsMessage = value;
    }

    /**
     * Gets the value of the isUsersMessage property.
     * 
     */
    public boolean isIsUsersMessage() {
        return isUsersMessage;
    }

    /**
     * Sets the value of the isUsersMessage property.
     * 
     */
    public void setIsUsersMessage(boolean value) {
        this.isUsersMessage = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

}
