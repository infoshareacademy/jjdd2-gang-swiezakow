
package pl.infoshareacademy.webapp.allegro.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BillingDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillingDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="billingId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="billingType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="billingFixperc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="billingName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="billingRangeFrom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="billingRangeTo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="billingCat" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="billingRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="billingAuctionType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillingDataType", propOrder = {

})
public class BillingDataType {

    protected int billingId;
    @XmlElement(required = true)
    protected String billingType;
    @XmlElement(required = true)
    protected String billingFixperc;
    @XmlElement(required = true)
    protected String billingName;
    @XmlElement(required = true)
    protected String billingRangeFrom;
    @XmlElement(required = true)
    protected String billingRangeTo;
    @XmlElement(required = true)
    protected String billingCat;
    @XmlElement(required = true)
    protected String billingRate;
    protected int billingAuctionType;

    /**
     * Gets the value of the billingId property.
     * 
     */
    public int getBillingId() {
        return billingId;
    }

    /**
     * Sets the value of the billingId property.
     * 
     */
    public void setBillingId(int value) {
        this.billingId = value;
    }

    /**
     * Gets the value of the billingType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingType() {
        return billingType;
    }

    /**
     * Sets the value of the billingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingType(String value) {
        this.billingType = value;
    }

    /**
     * Gets the value of the billingFixperc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingFixperc() {
        return billingFixperc;
    }

    /**
     * Sets the value of the billingFixperc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingFixperc(String value) {
        this.billingFixperc = value;
    }

    /**
     * Gets the value of the billingName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingName() {
        return billingName;
    }

    /**
     * Sets the value of the billingName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingName(String value) {
        this.billingName = value;
    }

    /**
     * Gets the value of the billingRangeFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingRangeFrom() {
        return billingRangeFrom;
    }

    /**
     * Sets the value of the billingRangeFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingRangeFrom(String value) {
        this.billingRangeFrom = value;
    }

    /**
     * Gets the value of the billingRangeTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingRangeTo() {
        return billingRangeTo;
    }

    /**
     * Sets the value of the billingRangeTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingRangeTo(String value) {
        this.billingRangeTo = value;
    }

    /**
     * Gets the value of the billingCat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingCat() {
        return billingCat;
    }

    /**
     * Sets the value of the billingCat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingCat(String value) {
        this.billingCat = value;
    }

    /**
     * Gets the value of the billingRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingRate() {
        return billingRate;
    }

    /**
     * Sets the value of the billingRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingRate(String value) {
        this.billingRate = value;
    }

    /**
     * Gets the value of the billingAuctionType property.
     * 
     */
    public int getBillingAuctionType() {
        return billingAuctionType;
    }

    /**
     * Sets the value of the billingAuctionType property.
     * 
     */
    public void setBillingAuctionType(int value) {
        this.billingAuctionType = value;
    }

}
