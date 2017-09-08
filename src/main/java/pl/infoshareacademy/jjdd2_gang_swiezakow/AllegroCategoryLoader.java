package pl.infoshareacademy.jjdd2_gang_swiezakow;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class AllegroCategoryLoader {

    public List<AllegroCategory> loadAllCategories() throws ParserConfigurationException, IOException, SAXException {

        InputStream xmlFile = getClass().getResourceAsStream("/Allegro_cathegories_2016-02-13.xml");

        DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbF.newDocumentBuilder();
        Document document = db.parse(xmlFile);

        document.getDocumentElement().normalize();
        NodeList nodeList = document.getDocumentElement()
                .getChildNodes().item(1)
                .getChildNodes().item(1)
                .getChildNodes().item(1)
                .getChildNodes();

        List<AllegroCategory> list = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
//            System.out.println("\nBieżący element: " + node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String sid = element.getElementsByTagName("ns1:catId").item(0).getTextContent();
                String sname = element.getElementsByTagName("ns1:catName").item(0).getTextContent();
                String sposition = element.getElementsByTagName("ns1:catPosition").item(0).getTextContent();
                String sparent = element.getElementsByTagName("ns1:catParent").item(0).getTextContent();

                int id = Integer.parseInt(sid);
                int parentId = Integer.parseInt(sparent);
                int position = Integer.parseInt(sposition);

                AllegroCategory acategory = new AllegroCategory(id, sname, parentId, position);

                list.add(acategory);

//                System.out.println("ID: " + sid);
//                System.out.println("Name: " + sname);
//                System.out.println("Parent: " + sparent);
//                System.out.println("Position: " + sposition);
            }
        }

        return list;
    }



    }




  /**  public class AllegroParents {

        AllegroParents loader = new AllegroParents();
        List<AllegroCategory> lista = loader.parents();
       for(int i = 0; i < AllegroParents.(); i++){
           if(lista.get(i).getParent() == 0) {
            AllegroCategory category = lista.get(i);
            System.out.println(category.getName());            }        }


        } **/

