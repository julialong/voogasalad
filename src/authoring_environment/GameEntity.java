package authoring_environment;

import java.util.ArrayList;
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
	
	private List<String> attributes;
	private ImageView image;
	
	public GameEntity() {
		attributes = new ArrayList<String>();
		
	}

	@Override
	public void updateAttributes(Attribute attribute, boolean status) {
		// TODO Auto-generated method stub
		
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

}
