package pl.infoshareacademy.webapp.allegro;

import pl.infoshareacademy.webapp.allegro.api.DoGetCatsDataRequest;
import pl.infoshareacademy.webapp.allegro.api.DoGetCatsDataResponse;
import pl.infoshareacademy.webapp.allegro.api.ServicePort;
import pl.infoshareacademy.webapp.allegro.api.ServiceService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AllegroClient {
    private static int COUNTRY=1;
    private static String KEY="702f4373";

        public void allegroClient() throws JAXBException {

        ServiceService serviceService = new ServiceService();
        ServicePort servicePort = serviceService.getServicePort();

        DoGetCatsDataRequest request = new DoGetCatsDataRequest();
        request.setCountryId(COUNTRY);
        request.setWebapiKey(KEY);

        DoGetCatsDataResponse response = servicePort.doGetCatsData(request);


        JAXBContext jaxbContext = JAXBContext.newInstance(DoGetCatsDataResponse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(response, sw);

        String xmlString = sw.toString();


    }
}
