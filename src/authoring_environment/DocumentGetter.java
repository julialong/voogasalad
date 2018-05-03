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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The DocumentGetter interface allows for the retrieval of an XML document
 * from any class. This code was appearing in several classes, and was refactored
 * to prevent code duplication.
 *
 * @author Michael Acker, Judith Sanchez, Julia Long
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
    	return getBasicAttribute(doc, "ImageFile");
 
    }

    default int getXDimension(Document doc) {
    		//Element child = doc.getElementById("Basic");
    		// return Integer.parseInt(child.getAttribute("XDimension"));
    		return Integer.parseInt(getBasicAttribute(doc, "XDimension"));
    }
    
    default int getYDimension(Document doc) {
		//Element child = doc.getElementById("Basic");
		//return Integer.parseInt(child.getAttribute("YDimension"));
    		return Integer.parseInt(getBasicAttribute(doc, "YDimension"));
    }
    
    default String getMovement(Document doc) {
    		//Element child = doc.getElementById("Movement");
    		//return child.getAttribute("MovementType");
    		NodeList children = doc.getElementsByTagName("Movement");
    		Element child = (Element) children.item(0);
    		return child.getAttribute("MovementType");
    }
    
    default List<String> getBehaviors(Document doc) {
    	return getNodeNames(doc, "Behavior");
    }
    
    default List<String> getInteractions(Document doc){
    	return getNodeNames(doc, "Interaction");
    }
    
    default List<String> getBehaviorAttributes(Document doc, String behavior) {
    	return getAttributes(doc, behavior);
    }
    
    default List<String> getInteractionAttributes(Document doc, String interaction) {
    	return getAttributes(doc, interaction);
    }

    default List<String> getPowerupAttributes(Document doc, String powerup) {
        return getAttributes(doc, powerup);
    }
    
    default String getBasicAttribute(Document doc, String attribute) {
    	NodeList children = doc.getElementsByTagName("Basic");
    	Element child = (Element) children.item(0);
    	return child.getAttribute(attribute);
    }
    
    default String getPowerUp(Document doc) {
    	NodeList powerUpNodes = doc.getElementsByTagName("AddPowerUp");
    	Element powerUpNode = (Element) powerUpNodes.item(0);
    	return powerUpNode.getAttribute("PowerUpType");
    }

    default String getWeapon(Document doc) {
        return doc.getDocumentElement().getAttribute("WeaponID");
    }
    
    default List<String> getNodeNames(Document doc, String type){
    	List<String> nameList = new ArrayList<String>();
    	NodeList children = doc.getElementsByTagName(type);
    	NodeList nodes = children.item(0).getChildNodes();
    	for (int k = 0; k < nodes.getLength(); k++) {
    		Node node = nodes.item(k);
    		if (!node.getNodeName().equals("#text")) {
	    		nameList.add(node.getNodeName());
    		}
    	}
    	return nameList;
    }
    
    default List<String> getAttributes(Document doc, String type) {
    	Map<String, String> attMap = new HashMap<String, String>();
    	NodeList typeNodes = doc.getElementsByTagName(type);
    Element child = (Element) typeNodes.item(0);
    	NamedNodeMap attributes = child.getAttributes();
    	for (int k = 0; k < attributes.getLength(); k++) {
    		Node node = attributes.item(k);
    		String name = node.getNodeName();
    		String value = node.getNodeValue();
    		attMap.put(name, value);
    	}
    	List<String> values = new ArrayList<String>(attMap.values());
    	return values;
    }


}
