package authoring_environment.game_elements;

import authoring_environment.DocumentGetter;
import authoring_environment.game_elements.factories.ObjectFactory;
import authoring_environment.game_elements.factories.PlatformObjectFactory;
import authoring_environment.grid.ScrollingGrid;
import engine.entity.GameEntity;
import engine.level.Level;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;


/**
 * The AuthoredLevel class is a mediator between the Level and ScrollingGrid.
 * This class takes in a Level and a ScrollingGrid and handles the interaction between
 * the two objects, keeping the frontend components of the ScrollingGrid separate from
 * the backend components of the Engine's Level class, while keeping them both up-to-date.
 * This class was written based on the Mediator design pattern, which allows communication
 * between two classes through some intermediate component.
 * This design allows the flexibility of using different Level and ScrollingGrid classes as
 * desired, as well as using different PlatformObjectFactory classes to create custom game elements
 * as desired.
 *
 * @author Julia Long
 * Date started: April 15 18
 */
public class AuthoredLevel implements DocumentGetter {

    private Level myLevel;
    private ScrollingGrid myScrollingGrid;
    private ObjectFactory myObjectFactory;

    /**
     * Creates a new authored level
     * @param level is the level
     * @param scrollingGrid is the scrolling grid
     */
    public AuthoredLevel(Level level, ScrollingGrid scrollingGrid) {
        myLevel = level;
        myScrollingGrid = scrollingGrid;
        myScrollingGrid.setMediator(this);
        myLevel.setColor(Color.web(level.getColor()));
        myObjectFactory = new PlatformObjectFactory(myLevel);
    }

    /**
     * Set the name of the Level object
     * @param name is the new name of the level
     */
    public void setName(String name) {
        myLevel.setName(name);
    }

    /**
     * Gets the name of the Level object
     * @return the name of the Level
     */
    public String getName() {
        return myLevel.getName();
    }

    /**
     * Sets the background of the grid
     * @param background is the new background for the grid
     */
    public void setBackground(Background background) {
        myScrollingGrid.setBackground(background);
    }

    /**
     * Sets the background color of the level object
     * @param color is the desired background color
     */
    public void setColor(Color color) {
        myLevel.setColor(color);
    }

    /**
     * Sets the new size of the level
     * @param x
     * @param y
     */
    public void setSize(double x, double y) {
        myScrollingGrid.resize((int)x, (int)y);
        myLevel.setSize(myScrollingGrid.getCellSize() * x, myScrollingGrid.getCellSize() * y);
    }

    /**
     * Gets the size of the level
     * @return a double array where the first value is the width of the level, and the second
     * is the height
     */
    public double[] getSize() {
        return myLevel.getSize();
    }

    /**
     * Gets the size of the grid
     * @return an int array where the first value is the number of gridcells across the width of ScrollingGrid,
     * and the second is the number of gridcells across the height
     */
    public int[] getGridSize() {
        return new int[]{myScrollingGrid.getCellArray().length, myScrollingGrid.getCellArray()[0].length};
    }

    /**
     * Gets current Level
     * @return this Level
     */
    public Level getLevel() {
        return myLevel;
    }

    /**
     * Gets current Scrolling Grid
     * @return Scrolling Grid
     */
    public ScrollingGrid getScrollingGrid() {
        return myScrollingGrid;
    }

    /**
     * Adds object to Level
     * @param ID is the ID of the object to create
     */
    public GameEntity addObject(String ID, double x, double y, double cellSize) {
        return myObjectFactory.addObject(ID, x, y, cellSize);
    }

    /**
     * Removes the object from the level
     * @param object is the object to remove
     */
    public void removeObject(GameEntity object){
        myLevel.getObjects().remove(object);
    }
}