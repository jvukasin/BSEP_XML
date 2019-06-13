
package com.xml.MegaTravelAgent.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
    "reservation",
    "sender",
    "content",
    "date",
    "receiver"
})
@Entity
@XmlRootElement(name = "Message", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message")
public class Message {

    @ManyToOne(fetch = FetchType.EAGER)
    @XmlElement(name = "Reservation", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/reservation", required = true)
    protected Reservation reservation;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message", required = true)
    protected TPerson sender;
	
	@Column(name = "content")
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message", required = true)
    protected String content;
	
	@Column(name = "dateTime")
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date date;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/Message", required = true)
    protected TPerson receiver;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute(name = "id")
    protected Long id;

	
    public Message() {
		super();
	}

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
     * Gets the value of the sender property.
     * 
     * @return
     *     possible object is
     *     {@link TPerson }
     *     
     */
    public TPerson getSender() {
        return sender;
    }

    /**
     * Sets the value of the sender property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPerson }
     *     
     */
    public void setSender(TPerson value) {
        this.sender = value;
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
     * Gets the value of the receiver property.
     * 
     * @return
     *     possible object is
     *     {@link TPerson }
     *     
     */
    public TPerson getReceiver() {
        return receiver;
    }

    /**
     * Sets the value of the receiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPerson }
     *     
     */
    public void setReceiver(TPerson value) {
        this.receiver = value;
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
