package authoring_environment;

import java.io.File;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 * @author Judi Sanchez
 * Date started: April 9 2018
 *
 */
public class CustomElementSaver {
	
	private DocumentBuilder docBuilder;
	private Document doc;
	private Double elementID; 
	
	/**
	 * 
	 * @param gameElment
	 * 	This is the gameEntity object that the attributes are being saved for
	 * @param id
	 * 	This is the ID for the gameEnity object that is being saved
	 * @param attributes
	 * 	This is a map from the attribute type to the chosen attribute for a category
	 * @throws TransformerException
	 */
	public CustomElementSaver(GameEntity gameElment, Double id, HashMap<String, String> attributes, String imageFile) throws TransformerException{
		elementID= id;
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		doc = docBuilder.newDocument();
		updateAttributes(attributes, imageFile);
		
	}
	
	private void updateAttributes(HashMap<String, String> attributes, String imageFile) throws TransformerException {
		Element customElement= doc.createElement("Attributes");
		doc.appendChild(customElement);
		for(String attribute: attributes.keySet()) {
			customElement.setAttribute(attribute, attributes.get(attribute));
		}
		customElement.setAttribute("ImageFile", imageFile);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("data/customElements/" + elementID.toString() + ".xml"));
		System.out.println("saved");
		transformer.transform(source, result);
		
	}

}