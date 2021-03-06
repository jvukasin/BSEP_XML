
package com.megatravel.authservice.model;

import java.util.ArrayList;
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
 *         &lt;element name="name">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="0"/>
 *               &lt;maxLength value="40"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="description">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="0"/>
 *               &lt;maxLength value="300"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="capacity">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cancellationPeriod">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="price">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="defaultPrice">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="type">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="0"/>
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ratingAvg">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit}Amenity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit}Image" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit}SpecificPrice" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/MegaTravel/global}Location"/>
 *       &lt;/sequence>
 *       &lt;attribute name="category">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *             &lt;minInclusive value="0"/>
 *             &lt;maxInclusive value="5"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
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
    "name",
    "description",
    "capacity",
    "cancellationPeriod",
    "price",
    "defaultPrice",
    "type",
    "ratingAvg",
    "amenity",
    "image",
    "specificPrice",
    "location"
})
@Entity
@XmlRootElement(name = "AccommodationUnit", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit")
public class AccommodationUnit {

    @Column(name = "name")
    @NotNull
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit", required = true)
    protected String name;

    @Column(name = "description")
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit", required = true)
    protected String description;

    @Column(name = "capacity")
    @NotNull
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit")
    protected int capacity;

    @Column(name = "cancellationPeriod")
    @NotNull
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit")
    protected int cancellationPeriod;

    @Column(name = "price")
    @NotNull
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit")
    protected double price;

    @Column(name = "defaultPrice")
    @NotNull
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit")
    protected double defaultPrice;

    @Column(name = "type")
    @NotNull
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit", required = true)
    protected String type;

    @Column(name = "ratingAvg")
    @NotNull
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit")
    protected double ratingAvg;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "accommodationunit_amenity",
            joinColumns = @JoinColumn(name = "accommodation_unit_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id", referencedColumnName = "id"))
    @XmlElement(name = "Amenity", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit")
    protected List<Amenity> amenity;

    @OneToMany(mappedBy = "accommodationUnit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @XmlElement(name = "Image", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit", required = true)
    protected List<Image> image;

    @OneToMany(mappedBy = "accommodationUnit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @XmlElement(name = "SpecificPrice", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/accommodation_unit")
    protected List<SpecificPrice> specificPrice;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @XmlElement(name = "Location", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/global", required = true)
    protected Location location;

    @Column(name = "category")
    @NotNull
    @XmlAttribute(name = "category")
    protected Integer category;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute(name = "id")
    protected Long id;

    @OneToMany(mappedBy = "accommodationUnit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Reservation> reservations;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected Agent agent;

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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the capacity property.
     * 
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the value of the capacity property.
     * 
     */
    public void setCapacity(int value) {
        this.capacity = value;
    }

    /**
     * Gets the value of the cancellationPeriod property.
     * 
     */
    public int getCancellationPeriod() {
        return cancellationPeriod;
    }

    /**
     * Sets the value of the cancellationPeriod property.
     * 
     */
    public void setCancellationPeriod(int value) {
        this.cancellationPeriod = value;
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
     * Gets the value of the defaultPrice property.
     * 
     */
    public double getDefaultPrice() {
        return defaultPrice;
    }

    /**
     * Sets the value of the defaultPrice property.
     * 
     */
    public void setDefaultPrice(double value) {
        this.defaultPrice = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the ratingAvg property.
     * 
     */
    public double getRatingAvg() {
        return ratingAvg;
    }

    /**
     * Sets the value of the ratingAvg property.
     * 
     */
    public void setRatingAvg(double value) {
        this.ratingAvg = value;
    }

    /**
     * Gets the value of the amenity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amenity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmenity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Amenity }
     * 
     * 
     */
    public List<Amenity> getAmenity() {
        if (amenity == null) {
            amenity = new ArrayList<Amenity>();
        }
        return this.amenity;
    }

    /**
     * Gets the value of the image property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the image property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Image }
     * 
     * 
     */
    public List<Image> getImage() {
        if (image == null) {
            image = new ArrayList<Image>();
        }
        return this.image;
    }

    /**
     * Gets the value of the specificPrice property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the specificPrice property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpecificPrice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpecificPrice }
     * 
     * 
     */
    public List<SpecificPrice> getSpecificPrice() {
        if (specificPrice == null) {
            specificPrice = new ArrayList<SpecificPrice>();
        }
        return this.specificPrice;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setLocation(Location value) {
        this.location = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCategory(Integer value) {
        this.category = value;
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

    public void setAmenity(List<Amenity> amenity) {
        this.amenity = amenity;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public void setSpecificPrice(List<SpecificPrice> specificPrice) {
        this.specificPrice = specificPrice;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
