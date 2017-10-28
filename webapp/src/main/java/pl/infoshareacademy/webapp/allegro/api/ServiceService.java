
package pl.infoshareacademy.webapp.allegro.api;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "serviceService", targetNamespace = "https://webapi.allegro.pl/service.php", wsdlLocation = "https://webapi.allegro.pl/service.php?wsdl")
public class ServiceService
    extends Service
{

    private final static URL SERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException SERVICESERVICE_EXCEPTION;
    private final static QName SERVICESERVICE_QNAME = new QName("https://webapi.allegro.pl/service.php", "serviceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://webapi.allegro.pl/service.php?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SERVICESERVICE_WSDL_LOCATION = url;
        SERVICESERVICE_EXCEPTION = e;
    }

    public ServiceService() {
        super(__getWsdlLocation(), SERVICESERVICE_QNAME);
    }

    public ServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SERVICESERVICE_QNAME, features);
    }

    public ServiceService(URL wsdlLocation) {
        super(wsdlLocation, SERVICESERVICE_QNAME);
    }

    public ServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICESERVICE_QNAME, features);
    }

    public ServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ServicePort
     */
    @WebEndpoint(name = "servicePort")
    public ServicePort getServicePort() {
        return super.getPort(new QName("https://webapi.allegro.pl/service.php", "servicePort"), ServicePort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ServicePort
     */
    @WebEndpoint(name = "servicePort")
    public ServicePort getServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("https://webapi.allegro.pl/service.php", "servicePort"), ServicePort.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SERVICESERVICE_EXCEPTION!= null) {
            throw SERVICESERVICE_EXCEPTION;
        }
        return SERVICESERVICE_WSDL_LOCATION;
    }

}
