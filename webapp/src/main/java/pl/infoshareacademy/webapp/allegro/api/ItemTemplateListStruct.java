
package pl.infoshareacademy.webapp.allegro.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ItemTemplateListStruct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ItemTemplateListStruct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="itemTemplateId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="itemTemplateName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="itemTemplateFields" type="{https://webapi.allegro.pl/service.php}ArrayOfFieldsvalue" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemTemplateListStruct", propOrder = {

})
public class ItemTemplateListStruct {

    protected int itemTemplateId;
    @XmlElement(required = true)
    protected String itemTemplateName;
    protected ArrayOfFieldsvalue itemTemplateFields;

    /**
     * Gets the value of the itemTemplateId property.
     * 
     */
    public int getItemTemplateId() {
        return itemTemplateId;
    }

    /**
     * Sets the value of the itemTemplateId property.
     * 
     */
    public void setItemTemplateId(int value) {
        this.itemTemplateId = value;
    }

    /**
     * Gets the value of the itemTemplateName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemTemplateName() {
        return itemTemplateName;
    }

    /**
     * Sets the value of the itemTemplateName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemTemplateName(String value) {
        this.itemTemplateName = value;
    }

    /**
     * Gets the value of the itemTemplateFields property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFieldsvalue }
     *     
     */
    public ArrayOfFieldsvalue getItemTemplateFields() {
        return itemTemplateFields;
    }

    /**
     * Sets the value of the itemTemplateFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFieldsvalue }
     *     
     */
    public void setItemTemplateFields(ArrayOfFieldsvalue value) {
        this.itemTemplateFields = value;
    }

}
