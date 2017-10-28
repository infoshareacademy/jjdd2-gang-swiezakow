
package pl.infoshareacademy.webapp.allegro.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CreatedItemTemplateStruct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreatedItemTemplateStruct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="itemTemplateId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreatedItemTemplateStruct", propOrder = {

})
public class CreatedItemTemplateStruct {

    protected int itemTemplateId;

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

}
