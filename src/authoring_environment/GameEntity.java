package authoring_environment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Judi Sanchez
 * Date started: April 3 2018
 *
 */
public class GameEntity implements AuthoredElement {
	
	private double elementID;
	private double xLocation;
	private double yLocation;
	
	private HashMap<String, String> attributes;
	private ImageView image;
	
	public GameEntity() {
		attributes = new HashMap<String, String>();
		setID();
		
	}

	@Override
	public void updateAttributes(Attribute attribute, boolean status) {
		// TODO Auto-generated method stub
		
	}

	public void updateAttributes(HashMap<String, String> newAttributes) {
		// TODO Auto-generated method stub
		attributes = newAttributes;
		
	}

	@Override
	public void uploadImage(String fileName) {
		image = new ImageView(fileName.toString());
		
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
	
	public double getID() {
		return elementID;
	}

}
