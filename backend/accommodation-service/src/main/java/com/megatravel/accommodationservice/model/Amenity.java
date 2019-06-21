
package com.megatravel.accommodationservice.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "name",
    "faIcon"
})
@Entity
@XmlRootElement(name = "Amenity", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit")
public class Amenity {

	@Column(name = "name")
	@NotNull
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit", required = true)
    protected String name;

    @Column(name = "faIcon")
    @XmlAttribute(name = "faIcon")
    protected String faIcon;
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute(name = "id")
    protected Long id;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "accommodationunit_amenity")
    protected List<AccommodationUnit> accommodationUnit;
    
    

    public List<AccommodationUnit> getAccommodationUnit() {
		return accommodationUnit;
	}

	public void setAccommodationUnit(List<AccommodationUnit> accommodationUnit) {
		this.accommodationUnit = accommodationUnit;
	}

	public Amenity() 
    {
		super();
	}

	/**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */

    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the faIcon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */

    public String getFaIcon() {
        return faIcon;
    }

    /**
     * Sets the value of the faIcon property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFaIcon(String value) {
        this.faIcon = value;
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
