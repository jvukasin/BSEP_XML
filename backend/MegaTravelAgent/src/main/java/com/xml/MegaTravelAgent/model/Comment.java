
package com.xml.MegaTravelAgent.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
 *         &lt;element name="approved" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="text">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="0"/>
 *               &lt;maxLength value="500"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "", propOrder = {
    "approved",
    "text",
    "postingDate"
})
@Entity
@XmlRootElement(name = "Comment", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/global")
public class Comment {

	@Column(name = "approved")
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/global", defaultValue = "false")
    protected boolean approved;

    @Column(name ="text")
    @NotNull
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/global", required = true)
    protected String text;

    @Column(name = "postingDate")
    @XmlSchemaType(name = "dateTime")
    protected Date postingDate;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    protected Rating rating;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

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
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */

    /**
     * Gets the value of the postingDate property.
     *
     * @return
     *     possible object is
     *     {@link Date }
     *
     */
    public Date getPostingDate() {
        return postingDate;
    }

    /**
     * Sets the value of the postingDate property.
     *
     * @param value
     *     allowed object is
     *     {@link Date }
     *
     */

    public void setPostingDate(Date value) {
        this.postingDate = value;
    }

    public void setText(String value) {
        this.text = value;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
