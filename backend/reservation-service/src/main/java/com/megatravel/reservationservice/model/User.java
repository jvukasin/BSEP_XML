
package com.megatravel.reservationservice.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;


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
 *         &lt;element name="status">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="active"/>
 *               &lt;enumeration value="inactive"/>
 *               &lt;enumeration value="blocked"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
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
    "status"
})
@XmlRootElement(name = "User", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/users")
@Entity
@DiscriminatorValue("USER")
public class User
    extends TPerson
{

    @Column(name = "status")
    @NotNull
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/MegaTravel/users", required = true)
    protected String status;

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

}
