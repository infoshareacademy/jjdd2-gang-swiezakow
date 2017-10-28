
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
 *         &lt;element name="itemTemplates" type="{https://webapi.allegro.pl/service.php}ItemTemplatesStruct"/>
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
    "itemTemplates"
})
@XmlRootElement(name = "doGetItemTemplatesResponse")
public class DoGetItemTemplatesResponse {

    @XmlElement(required = true)
    protected ItemTemplatesStruct itemTemplates;

    /**
     * Gets the value of the itemTemplates property.
     * 
     * @return
     *     possible object is
     *     {@link ItemTemplatesStruct }
     *     
     */
    public ItemTemplatesStruct getItemTemplates() {
        return itemTemplates;
    }

    /**
     * Sets the value of the itemTemplates property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemTemplatesStruct }
     *     
     */
    public void setItemTemplates(ItemTemplatesStruct value) {
        this.itemTemplates = value;
    }

}
