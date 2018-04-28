package authoring_environment.authored_elements;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.TransformerException;

import authoring_environment.Attribute;
import authoring_environment.CustomElementSaver;
import authoring_environment.DocumentGetter;
import authoring_environment.authored_elements.AuthoredElement;

/**
 * 
 * @author Judi Sanchez
 * Date started: April 3 2018
 * This class implements the AuthoredElement interface so it contains
 * methods to update the attributes of a custom element, upload a 
 * custom image, and update its location on the grid
 *
 */
public class GameElement implements AuthoredElement, DocumentGetter{
	
	private static final String ELEMENT_DATA_PATH = "./data/authoredElementData/";
	
	private String elementID;
	private Map<String, String> attributes;
	private String imageFile;
	private String xDimension;
	private String yDimension;
	
	/**
	 * This constructor is the default constructor. 
	 * It initiates attributes, which holds the current attributes
	 * of a custom element. It also gives the element a unique ID. 
	 */
	public GameElement() {
		attributes = new HashMap<>();
	
		
	}
	
	/**
	 * 
	 * @param idName
	 */
	public GameElement(String idName) {
		elementID = idName;
		setAttributesFromFile(idName);
		
		
	}

	@Override
	public void updateAttributes(Attribute attribute, boolean status) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @param newAttributes
	 */
	public void updateAttributes(Map<String, String> newAttributes) {
		attributes = newAttributes;
		try {
			CustomElementSaver saver = new CustomElementSaver(this, elementID, newAttributes, imageFile, xDimension, yDimension);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void uploadImage(String fileName) {
		imageFile = fileName;
		
	}

	@Override
	public void addToGrid(double xPosition, double yPosition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(double xPosition, double yPosition) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setID(String id) {
		elementID = id;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getID() {
		return elementID; 
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void updateDimensions(String x, String y) {
		xDimension = x;
		yDimension = y;
	}
	
	private void setAttributesFromFile(String idName) {
		getDocument(idName, ELEMENT_DATA_PATH);
		
	}
	
}
