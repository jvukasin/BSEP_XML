
package com.megatravel.reservationservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
 *         &lt;element name="coordinates" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="distanceFromCity">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/MegaTravel/global}City"/>
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
    "coordinates",
    "distanceFromCity",
    "city"
})
@Entity
@XmlRootElement(name = "Location", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/global")
public class Location {

	@Column(name = "coordinates")
	@NotNull
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/global", required = true)
    protected String coordinates;
	
	@Column(name = "distanceFromCity")
	@NotNull
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/global")
    protected double distanceFromCity;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotNull
    @XmlElement(name = "City", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/global", required = true)
    protected City city;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute(name = "id")
    protected Long id;
	
    @OneToOne(mappedBy = "location", fetch = FetchType.EAGER)
    protected AccommodationUnit accommodationUnit;
    
    

    public Location() {
		super();
	}

	public AccommodationUnit getAccommodationUnit() {
		return accommodationUnit;
	}

	public void setAccommodationUnit(AccommodationUnit accommodationUnit) {
		this.accommodationUnit = accommodationUnit;
	}

	/**
     * Gets the value of the coordinates property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the value of the coordinates property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoordinates(String value) {
        this.coordinates = value;
    }

    /**
     * Gets the value of the distanceFromCity property.
     * 
     */
    public double getDistanceFromCity() {
        return distanceFromCity;
    }

    /**
     * Sets the value of the distanceFromCity property.
     * 
     */
    public void setDistanceFromCity(double value) {
        this.distanceFromCity = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link City }
     *     
     */
    public City getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link City }
     *     
     */
    public void setCity(City value) {
        this.city = value;
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
