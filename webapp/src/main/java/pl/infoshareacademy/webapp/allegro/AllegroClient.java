package pl.infoshareacademy.webapp.allegro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.infoshareacademy.webapp.allegro.api.DoGetCatsDataRequest;
import pl.infoshareacademy.webapp.allegro.api.DoGetCatsDataResponse;
import pl.infoshareacademy.webapp.allegro.api.ServicePort;
import pl.infoshareacademy.webapp.allegro.api.ServiceService;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.WebServiceException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

@Startup
public class AllegroClient {

    private static int COUNTRY = 1;
    private static String KEY = "702f4373";
    private static final Logger logger = LogManager.getLogger(AllegroClient.class);

        @PostConstruct
        public void allegroClient () throws JAXBException, IOException, WebServiceException {
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
            String content = xmlString;
            FileOutputStream fop = null;
            File file;
            try {
                file = new File(System.getProperty("java.io.tmpdir") + "/file.xml");
                fop = new FileOutputStream(file);
                if (!file.exists()) {
                    file.createNewFile();
                }
                byte[] contentInBytes = content.getBytes();
                fop.write(contentInBytes);
                fop.flush();
                fop.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fop != null) {
                    fop.close();
                }
            }
        }
    }

