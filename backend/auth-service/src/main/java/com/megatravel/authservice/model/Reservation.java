
package com.megatravel.authservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
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
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="price">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit}AccommodationUnit"/>
 *         &lt;element name="isSuccessful" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "startDate",
    "endDate",
    "price",
    "accommodationUnit",
    "isSuccessful"
})
@Entity
@XmlRootElement(name = "Reservation", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/reservation")
public class Reservation {

    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/reservation", required = true)
    @XmlSchemaType(name = "date")
    @Column(name="startDate")
    @NotNull
    protected Date startDate;

    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/reservation", required = true)
    @XmlSchemaType(name = "date")
    @Column(name="endDate")
    @NotNull
    protected Date endDate;

    @Column(name="price")
    @NotNull
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/reservation")
    protected double price;

    @XmlElement(name = "AccommodationUnit", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit", required = true)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected AccommodationUnit accommodationUnit;

    @Column(name="isSuccessful")
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/reservation", defaultValue = "false")
    protected boolean isSuccessful;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute(name = "id")
    protected Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    protected TPerson reservator;

    @OneToMany(mappedBy="reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages;

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(Date value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(Date value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the accommodationUnit property.
     * 
     * @return
     *     possible object is
     *     {@link AccommodationUnit }
     *     
     */
    public AccommodationUnit getAccommodationUnit() {
        return accommodationUnit;
    }

    /**
     * Sets the value of the accommodationUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccommodationUnit }
     *     
     */
    public void setAccommodationUnit(AccommodationUnit value) {
        this.accommodationUnit = value;
    }

    /**
     * Gets the value of the isSuccessful property.
     * 
     */
    public boolean isIsSuccessful() {
        return isSuccessful;
    }

    /**
     * Sets the value of the isSuccessful property.
     * 
     */
    public void setIsSuccessful(boolean value) {
        this.isSuccessful = value;
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

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public TPerson getReservator() {
        return reservator;
    }

    public void setReservator(TPerson reservator) {
        this.reservator = reservator;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
