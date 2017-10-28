
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
 *         &lt;element name="createdItemTemplate" type="{https://webapi.allegro.pl/service.php}CreatedItemTemplateStruct"/>
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
    "createdItemTemplate"
})
@XmlRootElement(name = "doCreateItemTemplateResponse")
public class DoCreateItemTemplateResponse {

    @XmlElement(required = true)
    protected CreatedItemTemplateStruct createdItemTemplate;

    /**
     * Gets the value of the createdItemTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link CreatedItemTemplateStruct }
     *     
     */
    public CreatedItemTemplateStruct getCreatedItemTemplate() {
        return createdItemTemplate;
    }

    /**
     * Sets the value of the createdItemTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreatedItemTemplateStruct }
     *     
     */
    public void setCreatedItemTemplate(CreatedItemTemplateStruct value) {
        this.createdItemTemplate = value;
    }

}
