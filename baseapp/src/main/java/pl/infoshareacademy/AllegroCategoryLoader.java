package pl.infoshareacademy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllegroCategoryLoader {

    public List<AllegroCategory> loadAllCategories(String filename) {
        List<AllegroCategory> list = new ArrayList<>();

        try {
            Document document = loadDocument(filename);

            if (document == null) {
                return list;
            }

            document.getDocumentElement().normalize();
            NodeList nodeList = document.getDocumentElement()
                    .getChildNodes().item(1)
                    .getChildNodes().item(1) // check if has children?
                    .getChildNodes().item(1)
                    .getChildNodes();


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
        } catch (Exception e) {
            // zalogowac
        }

        return list;
    }

    public Map<Integer, List<AllegroCategory>> loadCategoryTree(String path) {
        Map<Integer, List<AllegroCategory>> categoryMap = new HashMap<>();
        for(AllegroCategory category : loadAllCategories(path)) {
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

    private Document loadDocument(String filename) {
        try {
            File initialFile = new File(filename);
            InputStream targetStream = new FileInputStream(initialFile);
            DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbF.newDocumentBuilder();
            return db.parse(targetStream);
        } catch (Exception e) {
            return null;
        }

    }
}
