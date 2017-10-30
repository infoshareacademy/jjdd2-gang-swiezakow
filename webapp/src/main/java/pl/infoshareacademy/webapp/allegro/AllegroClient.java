package pl.infoshareacademy.webapp.allegro;

import pl.infoshareacademy.webapp.allegro.api.DoGetCatsDataRequest;
import pl.infoshareacademy.webapp.allegro.api.DoGetCatsDataResponse;
import pl.infoshareacademy.webapp.allegro.api.ServicePort;
import pl.infoshareacademy.webapp.allegro.api.ServiceService;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;

public class AllegroClient {
    private static int COUNTRY = 1;
    private static String KEY = "702f4373";


    public static void main(String[] args) throws JAXBException, IOException {
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

        // wyswietli Ci w konsoli cale drzewo
        String xmlString = sw.toString();
        System.out.println(xmlString);
        String content = xmlString;


            FileOutputStream fop = null;
            File file = new File.
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            System.out.println("Done");





    }
}