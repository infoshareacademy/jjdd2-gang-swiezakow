
package pl.infoshareacademy.webapp.allegro.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MessageToBuyerStruct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MessageToBuyerStruct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="messageSellerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="messageContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageToBuyerStruct", propOrder = {

})
public class MessageToBuyerStruct {

    protected int messageSellerId;
    @XmlElement(required = true)
    protected String messageContent;

    /**
     * Gets the value of the messageSellerId property.
     * 
     */
    public int getMessageSellerId() {
        return messageSellerId;
    }

    /**
     * Sets the value of the messageSellerId property.
     * 
     */
    public void setMessageSellerId(int value) {
        this.messageSellerId = value;
    }

    /**
     * Gets the value of the messageContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * Sets the value of the messageContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageContent(String value) {
        this.messageContent = value;
    }

}
