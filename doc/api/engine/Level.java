package api.engine;

/**
 * The level interface is to be extended by each level class. There will likely be one level class
 * with varying parameters, but this allows for different kinds of level classes to be implemented easily
 * by extending this interface.
 */
public interface Level {
	/*
	 * Sets all GameObjects in the level to the input setID
	 * @param objects - a list of GameObjects
	 */
	public void setObjects(List<GameObject> objects);
	
	/*
	 * Adds a singular object to the level class
	 * @param object - a GameObject
	 */
	public void addObject(GameObject object);
	
	/*
	 * Returns all objects in a level
	 */
	public void getObjects(){}
	
	/*
	 * Sets the ID of the level for identification purposes
	 * @param id - The numerical ID for the level
	 */
	public void setID(int id);
}