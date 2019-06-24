
package com.xml.MegaTravelAgent.soap.reqres;

import java.util.ArrayList;
import java.util.List;
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
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/MegaTravel/soap_agent}AgentSOAP" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "agentSOAP"
})
@XmlRootElement(name = "FetchAgentsResponse", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_agent")
public class FetchAgentsResponse {

    @XmlElement(name = "AgentSOAP", namespace = "http://www.ftn.uns.ac.rs/MegaTravel/soap_agent", required = true)
    protected List<AgentSOAP> agentSOAP;

    /**
     * Gets the value of the agentSOAP property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the agentSOAP property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAgentSOAP().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AgentSOAP }
     * 
     * 
     */
    public List<AgentSOAP> getAgentSOAP() {
        if (agentSOAP == null) {
            agentSOAP = new ArrayList<AgentSOAP>();
        }
        return this.agentSOAP;
    }

}
