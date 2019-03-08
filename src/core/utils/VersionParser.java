package core.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class VersionParser {

    public String versionParser(String pathToConfig) {
        String version = "";
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File(pathToConfig));
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("radioServer");

            if (nodeList != null && nodeList.getLength() > 0) {
                for (int j = 0; j < nodeList.getLength(); j++) {
                    Element el = (org.w3c.dom.Element) nodeList.item(j);
                    version = el.getAttribute("version");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return version;
    }
}
