
package pl.infoshareacademy.webapp.allegro.api;

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
 *         &lt;element name="requestPayout" type="{https://webapi.allegro.pl/service.php}RequestPayoutStruct"/>
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
    "requestPayout"
})
@XmlRootElement(name = "doRequestPayoutResponse")
public class DoRequestPayoutResponse {

    @XmlElement(required = true)
    protected RequestPayoutStruct requestPayout;

    /**
     * Gets the value of the requestPayout property.
     * 
     * @return
     *     possible object is
     *     {@link RequestPayoutStruct }
     *     
     */
    public RequestPayoutStruct getRequestPayout() {
        return requestPayout;
    }

    /**
     * Sets the value of the requestPayout property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestPayoutStruct }
     *     
     */
    public void setRequestPayout(RequestPayoutStruct value) {
        this.requestPayout = value;
    }

}
