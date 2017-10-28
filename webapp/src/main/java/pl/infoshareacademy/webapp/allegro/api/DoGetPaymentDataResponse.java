
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
 *         &lt;element name="paymentList" type="{https://webapi.allegro.pl/service.php}ArrayOfBillingdatatype" minOccurs="0"/>
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
    "paymentList"
})
@XmlRootElement(name = "doGetPaymentDataResponse")
public class DoGetPaymentDataResponse {

    protected ArrayOfBillingdatatype paymentList;

    /**
     * Gets the value of the paymentList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfBillingdatatype }
     *     
     */
    public ArrayOfBillingdatatype getPaymentList() {
        return paymentList;
    }

    /**
     * Sets the value of the paymentList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfBillingdatatype }
     *     
     */
    public void setPaymentList(ArrayOfBillingdatatype value) {
        this.paymentList = value;
    }

}
