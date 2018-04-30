package authoring_environment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The DocumentGetter interface allows for the retrieval of an XML document
 * from any class. This code was appearing in several classes, and was refactored
 * to prevent code duplication.
 *
 * @author Judith Sanchez, Julia Long, Michael Acker
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
    	return getBasicAttribute(doc, "ImagePath");
 
    }

    default int getXDimension(Document doc) {
    		Element child = doc.getElementById("Basic");
    		return Integer.parseInt(child.getAttribute("XDimension"));
    }
    
    default int getYDimension(Document doc) {
		Element child = doc.getElementById("Basic");
		return Integer.parseInt(child.getAttribute("YDimension"));
    }
    
    default String getMovement(Document doc) {
    	Element child = doc.getElementById("Movement");
    	return child.getAttribute("MovementType");
    }
    
    default List<String> getBehaviors(Document doc) {
    	return getNodeNames(doc, "Behavior");
    }
    
    default List<String> getInteractions(Document doc){
    	return getNodeNames(doc, "Interaction");
    }
    
    default Map<String, String> getBehaviorAttributes(Document doc, String behavior) {
    	return getAttributes(doc, behavior);
    }
    
    default Map<String, String> getInteractionAttributes(Document doc, String interaction) {
    	return getAttributes(doc, interaction);
    }
    
    default String getBasicAttribute(Document doc, String attribute) {
    	Element child = doc.getElementById("Basic");
    	return child.getAttribute(attribute);
    }
    
    default String getPowerUp(Document doc) {
    	Element powerUpNode = doc.getElementById("AddPowerUp");
    	return powerUpNode.getAttribute("PowerUpType");
    }
    
    default List<String> getNodeNames(Document doc, String type){
    	List<String> nameList = new ArrayList<String>();
    	Element child = doc.getElementById(type);
    	NodeList nodes = child.getChildNodes();
    	for (int k = 0; k < nodes.getLength(); k++) {
    		Node node = nodes.item(k);
    		nameList.add(node.getNodeName());
    	}
    	return nameList;
    }
    
    default Map<String, String> getAttributes(Document doc, String type){
    	Map<String, String> attMap = new HashMap<String, String>();
    	Element typeNode = doc.getElementById(type);
    	NamedNodeMap attributes = typeNode.getAttributes();
    	for (int k = 0; k < attributes.getLength(); k++) {
    		Node node = attributes.item(k);
    		String name = node.getNodeName();
    		String value = node.getNodeValue();
    		attMap.put(name, value);
    	}
    	return attMap;
    }
}
