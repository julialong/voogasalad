package authoring_environment;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import authoring_environment.authored_elements.GameElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 * @author Judi Sanchez, Michael Acker
 * Date started: April 9 2018
 *
 */
public class CustomElementSaver {
	
	private DocumentBuilder docBuilder;
	private Document doc;
	private String elementID; 
	
	/**
	 * 
	 * @param gameElement
	 * 	This is the gameEntity object that the attributes are being saved for
	 * @param id
	 * 	This is the ID for the gameEnity object that is being saved
	 * @param attributes
	 * 	This is a map from the attribute type to the chosen attribute for a category
	 * @throws TransformerException
	 */

	public CustomElementSaver(String id, TreeNode treeNode) throws TransformerException{
		elementID= id;

		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		doc = docBuilder.newDocument();
		updateAttributes(treeNode);
		
	}
	
	private void updateAttributes(TreeNode treeNode) throws TransformerException {
		setXMLData(treeNode);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("data/authoredElementData/" + elementID.toString() + ".xml"));
		System.out.println("saved");
		transformer.transform(source, result);
	}
	
	private void setXMLData(TreeNode treeNode) {
		Element element = doc.createElement(treeNode.getInfo());
		doc.appendChild(element);
		for (TreeNode node : treeNode.getChildren()) {
			traverseXMLData(node, element);
		}
	}
	
	private void traverseXMLData(TreeNode treeNode, Element element) {
		if (!treeNode.getChildren().isEmpty()
				&& !treeNode.getChildren().get(0).getChildren().isEmpty()) {
			Element newElement = doc.createElement(treeNode.getInfo());
			element.appendChild(newElement);
			for (TreeNode node : treeNode.getChildren()) {
				traverseXMLData(node, newElement);
			}
		} else {
			for (TreeNode node : treeNode.getChildren()) {
				element.setAttribute(treeNode.getInfo(), node.getInfo());
			}
		}
	}
	
	

}
