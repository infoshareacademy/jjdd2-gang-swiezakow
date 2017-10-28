
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
 *         &lt;element name="cancelItemId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cancelBidsArray" type="{https://webapi.allegro.pl/service.php}ArrayOfInt" minOccurs="0"/>
 *         &lt;element name="cancelBidsReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cancelAddToBlackList" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
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
    "cancelItemId",
    "cancelBidsArray",
    "cancelBidsReason",
    "cancelAddToBlackList"
})
@XmlRootElement(name = "DoCancelBidItemRequest")
public class DoCancelBidItemRequest {

    @XmlElement(required = true)
    protected String sessionHandle;
    protected long cancelItemId;
    protected ArrayOfInt cancelBidsArray;
    @XmlElement(required = true)
    protected String cancelBidsReason;
    protected Long cancelAddToBlackList;

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
     * Gets the value of the cancelItemId property.
     * 
     */
    public long getCancelItemId() {
        return cancelItemId;
    }

    /**
     * Sets the value of the cancelItemId property.
     * 
     */
    public void setCancelItemId(long value) {
        this.cancelItemId = value;
    }

    /**
     * Gets the value of the cancelBidsArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getCancelBidsArray() {
        return cancelBidsArray;
    }

    /**
     * Sets the value of the cancelBidsArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setCancelBidsArray(ArrayOfInt value) {
        this.cancelBidsArray = value;
    }

    /**
     * Gets the value of the cancelBidsReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelBidsReason() {
        return cancelBidsReason;
    }

    /**
     * Sets the value of the cancelBidsReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelBidsReason(String value) {
        this.cancelBidsReason = value;
    }

    /**
     * Gets the value of the cancelAddToBlackList property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCancelAddToBlackList() {
        return cancelAddToBlackList;
    }

    /**
     * Sets the value of the cancelAddToBlackList property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCancelAddToBlackList(Long value) {
        this.cancelAddToBlackList = value;
    }

}
