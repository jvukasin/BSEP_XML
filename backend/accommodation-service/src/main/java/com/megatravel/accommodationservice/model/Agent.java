
package com.megatravel.accommodationservice.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
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
 *         &lt;element name="registrationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit}AccommodationUnit" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "", propOrder = {
        "registrationNumber",
        "accommodationUnit"
})
@XmlRootElement(name = "Agent", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/users")
@Entity
@DiscriminatorValue("AGENT")
public class Agent
        extends TPerson
{

    @Column(name="registrationNumber")
    @NotNull
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/users", required = true)
    protected String registrationNumber;

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @XmlElement(name = "AccommodationUnit", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit")
    protected List<AccommodationUnit> accommodationUnit;

    /**
     * Gets the value of the registrationNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Sets the value of the registrationNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRegistrationNumber(String value) {
        this.registrationNumber = value;
    }

    /**
     * Gets the value of the accommodationUnit property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accommodationUnit property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccommodationUnit().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccommodationUnit }
     *
     *
     */
    public List<AccommodationUnit> getAccommodationUnit() {
        if (accommodationUnit == null) {
            accommodationUnit = new ArrayList<AccommodationUnit>();
        }
        return this.accommodationUnit;
    }

}
