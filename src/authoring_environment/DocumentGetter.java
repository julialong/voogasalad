package authoring_environment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * The DocumentGetter interface allows for the retrieval of an XML document
 * from any class. This code was appearing in several classes, and was refactored
 * to prevent code duplication.
 *
 * @author Judith Sanchez, Julia Long
 */
public interface DocumentGetter {

    String XML = ".xml";

    /**
     * Gets the necessary document
     * @param ID is the ID of the object to retrieve
     * @param elementDataPath is the filepath to the folder
     * @return the retrieved Document
     */
    default Document getDocument(String ID, String elementDataPath){
        try {
            File file = new File(elementDataPath + ID + XML);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;
            db = dbf.newDocumentBuilder();
            return db.parse(file);
        } catch (Exception e) {
            //throw new InvalidElementException();
            return null;
        }
    }

    /**
     * Thrown when the file requested in invalid.
     */
    class InvalidElementException extends Exception {

    }
    
    default String getImagePath(Document doc) {
    		Element child = doc.getElementById("Basic");
    		return child.getAttribute("ImageFile");
 
    }
    
    default String getXDimension(Document doc) {
    		Element child = doc.getElementById("Basic");
    		return child.getAttribute("XDimension");
    }
    
    default String getYDimension(Document doc) {
		Element child = doc.getElementById("Basic");
		return child.getAttribute("YDimension");
    }
    
    /*default Map<String, List<String>> getBehaviors(Document doc){
    		Element child = doc.getElementById("Behaviors");
    		Map<String, List<S>>
    }*/
}
