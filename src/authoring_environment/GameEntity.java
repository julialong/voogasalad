package authoring_environment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.transform.TransformerException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Judi Sanchez
 * Date started: April 3 2018
 * This class implements the AuthoredElement interface so it contains
 * methods to update the attributes of a custom element, upload a 
 * custom image, and update its location on the grid
 *
 */
public class GameEntity implements AuthoredElement {
	
	private double elementID;
	private double xLocation;
	private double yLocation;
	
	private HashMap<String, String> attributes;
	private String imageFile;
	
	/**
	 * This constructor is the default constructor. 
	 * It initiates attributes, which holds the current attributes
	 * of a custom element. It also gives the element a unique ID. 
	 */
	public GameEntity() {
		attributes = new HashMap<String, String>();
		setID();
		
	}

	@Override
	public void updateAttributes(Attribute attribute, boolean status) {
		// TODO Auto-generated method stub
		
	}

	public void updateAttributes(HashMap<String, String> newAttributes) {
		attributes = newAttributes;
		try {
			CustomElementSaver saver = new CustomElementSaver(this, elementID, newAttributes, imageFile);
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
	
	private void setID() {
		File folder = new File("./data/customElements");
		File[] files = folder.listFiles((f, name) -> name.endsWith(".xml"));
		elementID = files.length + 1 ; 
	}
	
	public Double getID() {
		return elementID; 
	}
	
}
