package pl.infoshareacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(AllegroCategoryLoader.class);

    public List<AllegroCategory> loadAllCategories(String filename) {
        Document document = loadDocument(filename);
        List<AllegroCategory> list = new ArrayList<>();

        if (document == null) {
            logger.error("no file found");
            logger.warn("returned empty list");
            return new ArrayList<>();
        }
        try {
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getDocumentElement()
                    .getChildNodes().item(1)
                    .getChildNodes().item(1)
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
        } catch (Exception e){
            logger.error("caught an exception during loading all categories", e);

        }
        logger.debug("returned category list with " + list.size() + " elements");
        return list;
    }

    public Map<Integer, List<AllegroCategory>> loadCategoryTree(List<AllegroCategory> categories) {
        Map<Integer, List<AllegroCategory>> categoryMap = new HashMap<>();
        for(AllegroCategory category : categories) {
            if(!categoryMap.containsKey(category.getParent())){
                List<AllegroCategory> list = new ArrayList<>();
                list.add(category);
                categoryMap.put(category.getParent(), list);
            } else {
                categoryMap.get(category.getParent()).add(category);
            }
        }
        logger.debug("returned category map with " + categoryMap.size() + " elements");
        return categoryMap;
    }

    public Map<Integer, List<AllegroCategory>> loadCategoryTree(String path) {
        return loadCategoryTree(loadAllCategories(path));
    }

    private Document loadDocument(String filename) {
        try {
            File initialFile = new File(filename);
            InputStream targetStream = new FileInputStream(initialFile);
            DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbF.newDocumentBuilder();
            return db.parse(targetStream);
        } catch (Exception e) {
            logger.error("caught an exception during loading document", e);
            logger.warn("returned null");
            return null;
        }
    }
}