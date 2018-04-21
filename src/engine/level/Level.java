package engine.level;

import java.util.List;

import authoring_environment.grid.ScrollingGrid;
import engine.entity.GameEntity;

/**
 * The level interface is to be extended by each level class. There will likely be one level class
 * with varying parameters, but this allows for different kinds of level classes to be implemented easily
 * by extending this interface.
 *
 * @author Marcus Oertle, Robert Gitau, Julia Long
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

//	/**
//	 * Sets the current state of the grid for the level
//	 * @param grid is the grid to update to
//	 */
//	void updateGrid(ScrollingGrid grid);
//
//	/**
//	 * Gets the GridPane associated with the level
//	 * @return the grid of the level
//	 */
//	ScrollingGrid getGrid();
//
	/**
	 * Sets the x size of the grid.
	 * @param X is the new x size
	 * @param Y is the new y size
	 */
	void setSize(double X, double Y);
    
    /**
     * Updates the contents of the level
     */
     void update();

}