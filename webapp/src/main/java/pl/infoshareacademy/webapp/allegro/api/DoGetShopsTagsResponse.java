
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
 *         &lt;element name="shopsTagsCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="shopsTags" type="{https://webapi.allegro.pl/service.php}ArrayOfShopstagtypestruct" minOccurs="0"/>
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
    "shopsTagsCount",
    "shopsTags"
})
@XmlRootElement(name = "doGetShopsTagsResponse")
public class DoGetShopsTagsResponse {

    protected int shopsTagsCount;
    protected ArrayOfShopstagtypestruct shopsTags;

    /**
     * Gets the value of the shopsTagsCount property.
     * 
     */
    public int getShopsTagsCount() {
        return shopsTagsCount;
    }

    /**
     * Sets the value of the shopsTagsCount property.
     * 
     */
    public void setShopsTagsCount(int value) {
        this.shopsTagsCount = value;
    }

    /**
     * Gets the value of the shopsTags property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfShopstagtypestruct }
     *     
     */
    public ArrayOfShopstagtypestruct getShopsTags() {
        return shopsTags;
    }

    /**
     * Sets the value of the shopsTags property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfShopstagtypestruct }
     *     
     */
    public void setShopsTags(ArrayOfShopstagtypestruct value) {
        this.shopsTags = value;
    }

}
