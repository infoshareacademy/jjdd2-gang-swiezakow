package pl.infoshareacademy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllegroCategoryLoader {

    public List<AllegroCategory> loadAllCategories() throws ParserConfigurationException, IOException, SAXException {

        File xmlFile = new File("Allegro_cathegories_2016-02-13.xml");

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
            }
        }
        return list;
    }

    public Map<Integer, List<AllegroCategory>> loadCategoryTree() throws IOException, SAXException, ParserConfigurationException {
        Map<Integer, List<AllegroCategory>> categoryMap = new HashMap<>();
        for(AllegroCategory category : loadAllCategories()){
            if(!categoryMap.containsKey(category.getParent())){
                List<AllegroCategory> list = new ArrayList<>();
                list.add(category);
                categoryMap.put(category.getParent(), list);
            } else {
                categoryMap.get(category.getParent()).add(category);
            }
        }
        return categoryMap;
    }
}
