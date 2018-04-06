package engine.level;

import java.util.List;
import engine.entity.GameObject;
import javafx.scene.layout.GridPane;

/**
 * The level interface is to be extended by each level class. There will likely be one level class
 * with varying parameters, but this allows for different kinds of level classes to be implemented easily
 * by extending this interface.
 *
 * @author **ADD GAME ENGINE AUTHOR**, Julia Long
 */
public interface Level {

	/**
	 * Sets all GameObjects in the level to the input setID
	 * @param objects - a list of GameObjects
	 */
	void setObjects(List<GameObject> objects);
	
	/**
	 * Adds a singular object to the level class
	 * @param object - a GameObject
	 */
	void addObject(GameObject object);
	
	/**
	 * Returns all objects in a level
	 */
	List<GameObject> getObjects();
	
	/**
	 * Sets the ID of the level for identification purposes
	 * @param id - The numerical ID for the level
	 */
	void setID(int id);

	/**
	 * Sets the name of the level
	 * @param name is the name of the level
	 */
	void setName(String name);

	/**
	 * Sets the current state of the grid for the level
	 * @param grid is the grid to update to
	 */
	void updateGrid(GridPane grid);

	/**
	 * Gets the GridPane associated with the level
	 * @return the grid of the level
	 */
	GridPane getGrid();
}