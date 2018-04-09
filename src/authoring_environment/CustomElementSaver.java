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
	
	public CustomElementSaver(GameEntity gameElment, Double id, HashMap<String, String> attributes) throws TransformerException{
		elementID= id;
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc = docBuilder.newDocument();
		updateAttributes(attributes);
		
	}
	
	private void updateAttributes(HashMap<String, String> attributes) throws TransformerException {
		Element customElement= doc.createElement("customElement");
		doc.appendChild(customElement);
		for(String attribute: attributes.keySet()) {
			customElement.setAttribute(attribute, attributes.get(attribute));
			//customElement.setAttributeNode(newAttr)
			System.out.println("Attributes: " + 
			attribute + attributes.get(attribute));
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("data/customElements/" + elementID.toString() + ".xml"));
		System.out.println("saved");
		transformer.transform(source, result);
		
	}

}
