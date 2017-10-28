
package pl.infoshareacademy.webapp.allegro.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MyContactList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MyContactList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="contactUserId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="contactNick" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactLastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactStreet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactPostcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactCountry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactPhone2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactRating" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactBlocked" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MyContactList", propOrder = {

})
public class MyContactList {

    protected int contactUserId;
    @XmlElement(required = true)
    protected String contactNick;
    @XmlElement(required = true)
    protected String contactFirstName;
    @XmlElement(required = true)
    protected String contactLastName;
    @XmlElement(required = true)
    protected String contactCompany;
    @XmlElement(required = true)
    protected String contactEmail;
    @XmlElement(required = true)
    protected String contactStreet;
    @XmlElement(required = true)
    protected String contactPostcode;
    @XmlElement(required = true)
    protected String contactCity;
    @XmlElement(required = true)
    protected String contactCountry;
    @XmlElement(required = true)
    protected String contactPhone;
    @XmlElement(required = true)
    protected String contactPhone2;
    @XmlElement(required = true)
    protected String contactRating;
    @XmlElement(required = true)
    protected String contactBlocked;

    /**
     * Gets the value of the contactUserId property.
     * 
     */
    public int getContactUserId() {
        return contactUserId;
    }

    /**
     * Sets the value of the contactUserId property.
     * 
     */
    public void setContactUserId(int value) {
        this.contactUserId = value;
    }

    /**
     * Gets the value of the contactNick property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactNick() {
        return contactNick;
    }

    /**
     * Sets the value of the contactNick property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactNick(String value) {
        this.contactNick = value;
    }

    /**
     * Gets the value of the contactFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactFirstName() {
        return contactFirstName;
    }

    /**
     * Sets the value of the contactFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactFirstName(String value) {
        this.contactFirstName = value;
    }

    /**
     * Gets the value of the contactLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactLastName() {
        return contactLastName;
    }

    /**
     * Sets the value of the contactLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactLastName(String value) {
        this.contactLastName = value;
    }

    /**
     * Gets the value of the contactCompany property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactCompany() {
        return contactCompany;
    }

    /**
     * Sets the value of the contactCompany property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactCompany(String value) {
        this.contactCompany = value;
    }

    /**
     * Gets the value of the contactEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Sets the value of the contactEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactEmail(String value) {
        this.contactEmail = value;
    }

    /**
     * Gets the value of the contactStreet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactStreet() {
        return contactStreet;
    }

    /**
     * Sets the value of the contactStreet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactStreet(String value) {
        this.contactStreet = value;
    }

    /**
     * Gets the value of the contactPostcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPostcode() {
        return contactPostcode;
    }

    /**
     * Sets the value of the contactPostcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPostcode(String value) {
        this.contactPostcode = value;
    }

    /**
     * Gets the value of the contactCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactCity() {
        return contactCity;
    }

    /**
     * Sets the value of the contactCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactCity(String value) {
        this.contactCity = value;
    }

    /**
     * Gets the value of the contactCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactCountry() {
        return contactCountry;
    }

    /**
     * Sets the value of the contactCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactCountry(String value) {
        this.contactCountry = value;
    }

    /**
     * Gets the value of the contactPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * Sets the value of the contactPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPhone(String value) {
        this.contactPhone = value;
    }

    /**
     * Gets the value of the contactPhone2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPhone2() {
        return contactPhone2;
    }

    /**
     * Sets the value of the contactPhone2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPhone2(String value) {
        this.contactPhone2 = value;
    }

    /**
     * Gets the value of the contactRating property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactRating() {
        return contactRating;
    }

    /**
     * Sets the value of the contactRating property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactRating(String value) {
        this.contactRating = value;
    }

    /**
     * Gets the value of the contactBlocked property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactBlocked() {
        return contactBlocked;
    }

    /**
     * Sets the value of the contactBlocked property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactBlocked(String value) {
        this.contactBlocked = value;
    }

}
