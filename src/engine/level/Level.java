package engine.level;

import java.util.List;

import engine.entity.GameEntity;
import javafx.scene.paint.Color;

/**
 * The level interface is to be extended by each level class. There will likely be one level class
 * with varying parameters, but this allows for different kinds of level classes to be implemented easily
 * by extending this interface.
 *
 * @author Julia Long, Marcus Oertle, Robert Gitau
 */
public interface Level {

	/**
	 * Sets all GameObjects in the level to the input setID
	 * @param objects - a list of GameObjects
	 */
	void setObjects(List<GameEntity> objects);
	
	/**
	 * Adds a singular object to the level class
	 * @param object - a GameObject
	 */
	void addObject(GameEntity object);
	
	/**
	 * Returns all objects in a level
	 */
	List<GameEntity> getObjects();
	
	/**
	 * Sets the ID of the level for identification purposes
	 * @param id - The numerical ID for the level
	 */
	void setID(int id);

	/**
	 * Returns the integer ID of the level
	 * @return the integer ID
	 */
	int getID();

	/**
	 * Sets the name of the level
	 * @param name is the name of the level
	 */
	void setName(String name);

	/**
	 * Gets the name of the level
	 * @return the name of the level
	 */
	String getName();

	/**
	 * Sets the x size of the grid.
	 * @param X is the new x size
	 * @param Y is the new y size
	 */
	void setSize(double X, double Y);


	double[] getSize();

	/**
	 * Returns true if level is complete
	 */
	boolean getLevelComplete();

	void setColor(Color color);

	/**
	 * Retrieves the background color of the level
	 * @return color of level
	 */
	String getColor();


    /**
     * Updates the contents of the level
     */
     void update();

}