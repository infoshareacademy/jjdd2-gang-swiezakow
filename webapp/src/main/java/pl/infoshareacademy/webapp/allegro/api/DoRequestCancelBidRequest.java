
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
 *         &lt;element name="sessionHandle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="requestItemId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="requestCancelReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "sessionHandle",
    "requestItemId",
    "requestCancelReason"
})
@XmlRootElement(name = "DoRequestCancelBidRequest")
public class DoRequestCancelBidRequest {

    @XmlElement(required = true)
    protected String sessionHandle;
    protected long requestItemId;
    @XmlElement(required = true)
    protected String requestCancelReason;

    /**
     * Gets the value of the sessionHandle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionHandle() {
        return sessionHandle;
    }

    /**
     * Sets the value of the sessionHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionHandle(String value) {
        this.sessionHandle = value;
    }

    /**
     * Gets the value of the requestItemId property.
     * 
     */
    public long getRequestItemId() {
        return requestItemId;
    }

    /**
     * Sets the value of the requestItemId property.
     * 
     */
    public void setRequestItemId(long value) {
        this.requestItemId = value;
    }

    /**
     * Gets the value of the requestCancelReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestCancelReason() {
        return requestCancelReason;
    }

    /**
     * Sets the value of the requestCancelReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestCancelReason(String value) {
        this.requestCancelReason = value;
    }

}
