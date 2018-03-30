package usecases;

import api.gae.AuthoredElement;

public class AddElement {
	
	AuthoredElement myAuthoredElement;
	
	/**
	 * Adds a new element of a certain type to the level in a specified grid position
	 * @param gridX x position on grid
	 * @param gridY y position on grid
	 * @param ID String ID of element type
	 */
	public void addNewElement(int gridX, int gridY, String ID) {
		//Calls get type to determine what class the element should be from its ID
		String type = AuthoredElement.getType(ID);
		//Switch statement creates a new object of the necessary class. 
		switch(type) {
		case "SampleBlock": 
			myAuthoredElement = new SampleBlock(gridX, gridY, ID);
			//Adds new element to the level's grid.
			myAuthoredElement.addToGrid();
		}
	}
	
	/**
	 * Determines the type of object the element is based on its ID and returns a string representation of it.
	 * @param ID String ID of element type
	 * @return String representation of element class
	 */
	private String getElementType(String ID) {
		//This will be accomplished by checking the element's properties file, but in this case, it is assumed that the type is SampleBlock.
		return "SampleBlock";

//Alternate, possibly complementary approach using the Level interface. 
/*
 * Adds elements to a level.
 */
public class AddElement {
	public AddElement(Level level, GameObject object) {
		level.addObject(object);
	}
	
	public AddElement(Level level, List<GameObject> objects){
		level.setObjects(objects);
>>>>>>> master
	}
}