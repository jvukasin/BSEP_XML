
package com.xml.MegaTravelAgent.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
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
 *     &lt;extension base="{http://www.ftn.uns.ac.rs/MegaTravel/global}TPerson">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/MegaTravel/reservation}Reservation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="status">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="active"/>
 *               &lt;enumeration value="inactive"/>
 *               &lt;enumeration value="blocked"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/MegaTravel/global}Rating" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "status",
    "rating"
})
@XmlRootElement(name = "User", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/users")

@Entity
@DiscriminatorValue("USER")
public class User
    extends TPerson
{

    @XmlElement(name = "Reservation", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/reservation")
    @OneToMany(mappedBy = "reservator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Reservation> reservation;

    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/users", required = true)
    @Column(name="status")
    @NotNull
    protected String status;


    @XmlElement(name = "Rating", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/global")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Rating> rating;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the rating property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rating property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRating().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Rating }
     * 
     * 
     */
    public List<Rating> getRating() {
        if (rating == null) {
            rating = new ArrayList<Rating>();
        }
        return this.rating;
    }
}
