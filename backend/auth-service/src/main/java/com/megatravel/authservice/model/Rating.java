
package com.megatravel.authservice.model;

import javax.xml.bind.annotation.*;
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
 *         &lt;element name="value">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *               &lt;maxInclusive value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="approved" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="postingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="comment">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="0"/>
 *               &lt;maxLength value="300"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="reservator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accommodation_id" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "value",
    "approved",
    "postingDate",
    "comment",
    "reservator",
    "accommodationId"
})
@XmlRootElement(name = "Rating", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/global")
public class Rating {

    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/global")
    protected int value;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/global", defaultValue = "false")
    protected boolean approved;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/global", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar postingDate;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/global", required = true)
    protected String comment;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/global", required = true)
    protected String reservator;
    @XmlElement(name = "accommodation_id", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/global")
    protected long accommodationId;
    @XmlAttribute(name = "id")
    protected Long id;

    /**
     * Gets the value of the value property.
     * 
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets the value of the approved property.
     * 
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * Sets the value of the approved property.
     * 
     */
    public void setApproved(boolean value) {
        this.approved = value;
    }

    /**
     * Gets the value of the postingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPostingDate() {
        return postingDate;
    }

    /**
     * Sets the value of the postingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPostingDate(XMLGregorianCalendar value) {
        this.postingDate = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the reservator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReservator() {
        return reservator;
    }

    /**
     * Sets the value of the reservator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReservator(String value) {
        this.reservator = value;
    }

    /**
     * Gets the value of the accommodationId property.
     * 
     */
    public long getAccommodationId() {
        return accommodationId;
    }

    /**
     * Sets the value of the accommodationId property.
     * 
     */
    public void setAccommodationId(long value) {
        this.accommodationId = value;
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
