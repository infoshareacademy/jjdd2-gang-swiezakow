
package pl.infoshareacademy.webapp.allegro.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="cancelBidValue" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cancelledBids" type="{https://webapi.allegro.pl/service.php}ArrayOfInt" minOccurs="0"/>
 *         &lt;element name="notCancelledBids" type="{https://webapi.allegro.pl/service.php}ArrayOfInt" minOccurs="0"/>
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
    "cancelBidValue",
    "cancelledBids",
    "notCancelledBids"
})
@XmlRootElement(name = "doCancelBidItemResponse")
public class DoCancelBidItemResponse {

    protected int cancelBidValue;
    protected ArrayOfInt cancelledBids;
    protected ArrayOfInt notCancelledBids;

    /**
     * Gets the value of the cancelBidValue property.
     * 
     */
    public int getCancelBidValue() {
        return cancelBidValue;
    }

    /**
     * Sets the value of the cancelBidValue property.
     * 
     */
    public void setCancelBidValue(int value) {
        this.cancelBidValue = value;
    }

    /**
     * Gets the value of the cancelledBids property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getCancelledBids() {
        return cancelledBids;
    }

    /**
     * Sets the value of the cancelledBids property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setCancelledBids(ArrayOfInt value) {
        this.cancelledBids = value;
    }

    /**
     * Gets the value of the notCancelledBids property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getNotCancelledBids() {
        return notCancelledBids;
    }

    /**
     * Sets the value of the notCancelledBids property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setNotCancelledBids(ArrayOfInt value) {
        this.notCancelledBids = value;
    }

}
