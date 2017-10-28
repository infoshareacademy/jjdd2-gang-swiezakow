
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
 *         &lt;element name="mycontactList" type="{https://webapi.allegro.pl/service.php}ArrayOfMycontactlist" minOccurs="0"/>
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
    "mycontactList"
})
@XmlRootElement(name = "doMyContactResponse")
public class DoMyContactResponse {

    protected ArrayOfMycontactlist mycontactList;

    /**
     * Gets the value of the mycontactList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMycontactlist }
     *     
     */
    public ArrayOfMycontactlist getMycontactList() {
        return mycontactList;
    }

    /**
     * Sets the value of the mycontactList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMycontactlist }
     *     
     */
    public void setMycontactList(ArrayOfMycontactlist value) {
        this.mycontactList = value;
    }

}
