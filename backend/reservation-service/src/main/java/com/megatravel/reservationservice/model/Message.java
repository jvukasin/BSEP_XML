package com.megatravel.reservationservice.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Date;


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
 *         &lt;element name="sender" type="{http://www.ftn.uns.ac.rs/MegaTravel/global}TPerson"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="receiver" type="{http://www.ftn.uns.ac.rs/MegaTravel/global}TPerson"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "", propOrder = {
    "idReservation",
    "content",
    "date",
    "usernameSender",
    "usernameReceiver",
    "isAgentsMessage",
    "isUsersMessage"
})
@Entity
@XmlRootElement(name = "Message", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message")
public class Message {


    @Column(name = "content")
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message", required = true)
    protected String content;
	
	@Column(name = "dateTime")
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date date;

	@Transient
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message", required = true)
    protected String usernameSender;

    @Transient
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message", required = true)
    protected String usernameReceiver;

    @Transient
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message")
    protected long idReservation;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute(name = "id")
    protected Long id;
	
    @ManyToOne(fetch = FetchType.EAGER)
    protected TPerson receiver;

    @ManyToOne(fetch = FetchType.EAGER)
    protected Reservation reservation;

    @ManyToOne(fetch = FetchType.EAGER)
    protected TPerson sender;

    @Column(name = "isUsersMessage")
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message")
    protected boolean isUsersMessage;

    @Column(name = "isAgentsMessage")
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message")
    protected boolean isAgentsMessage;



    public Message() {
		super();
	}



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
     *     {@link Date }
     *     
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDate(Date value) {
        this.date = value;
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

    /**
     * Gets the value of the idReservation  property.
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
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */

    public TPerson getReceiver() {
        return receiver;
    }

    public void setReceiver(TPerson receiver) {
        this.receiver = receiver;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public TPerson getSender() {
        return sender;
    }

    public void setSender(TPerson sender) {
        this.sender = sender;
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
}
