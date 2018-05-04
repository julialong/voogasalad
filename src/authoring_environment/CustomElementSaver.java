package authoring_environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

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
 * @author Judi Sanchez, Michael Acker Date started: April 9 2018
 *
 */
public class CustomElementSaver implements DataAlert {

	private static final String ELEMENT_RESOURCES = "resources.Elements";
	private static final int INCREMENT = 1;
	
	private DocumentBuilder docBuilder;
	private Document doc;
	private String elementID;
	private Integer count;

	/**
	 * 
	 * @param gameElement
	 *            This is the gameEntity object that the attributes are being saved
	 *            for
	 * @param id
	 *            This is the ID for the gameEnity object that is being saved
	 * @param attributes
	 *            This is a map from the attribute type to the chosen attribute for
	 *            a category
	 * @throws TransformerException
	 */

	public CustomElementSaver(String id, TreeNode treeNode) throws TransformerException {
		elementID = id;

		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			saveAlert(e);
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
		transformer.transform(source, result);
	}

	private void setXMLData(TreeNode treeNode) {
		Element element = doc.createElement(treeNode.getInfo());
		doc.appendChild(element);
		count = 0;
		for (TreeNode node : treeNode.getChildren()) {
			traverseXMLData(node, element);
		}
	}

	private void traverseXMLData(TreeNode treeNode, Element element) {
		if (isACategory(treeNode)) {
			Element newElement = doc.createElement(treeNode.getInfo());
			element.appendChild(newElement);
			for (TreeNode node : treeNode.getChildren()) {
				traverseXMLData(node, newElement);
			}
		} else if (!(treeNode.getChildren().size() == 0)) {
			for (TreeNode node : treeNode.getChildren()) {
				element.setAttribute(treeNode.getInfo(), node.getInfo());
			}
		}
		else {
			count = count + INCREMENT;
			element.setAttribute( "Point" + count.toString(), treeNode.getInfo());
		}
	}

	private Boolean isACategory(TreeNode node) {
		ResourceBundle rb = ResourceBundle.getBundle(ELEMENT_RESOURCES);
		Enumeration<String> allElements = rb.getKeys();
		List<String> elementOptions = new ArrayList<>();
		elementOptions = Collections.list(allElements);
		String info = node.getInfo();
		return elementOptions.contains(info);
	}

}
