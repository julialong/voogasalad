package authoring_environment.game_elements;

import authoring_environment.DocumentGetter;
import authoring_environment.grid.ScrollingGrid;
import engine.behavior.Behavior;
import engine.entity.GameEntity;
import engine.interaction.Interaction;
import engine.level.Level;
import engine.movement.Movement;
import engine.powerup.PowerUp;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import org.w3c.dom.Document;

import java.lang.reflect.Constructor;


/**
 * The AuthoredLevel class is a mediator between the Level and ScrollingGrid
 * class
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
        myLevel.setColor(Color.WHITE);
        myObjectFactory = new ObjectFactory(myLevel);
    }

    /**
     * Set the name of the Level object
     * @param name is the new name of the level
     */
    public void setName(String name) {
        myLevel.setName(name);
    }

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

    public void setImagePath(String imagePath) {
        // myLevel.setImage(imagePath)
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

    public double[] getSize() {
        return myLevel.getSize();
    }

    public int[] getGridSize() {
        int[] lengthArray = {myScrollingGrid.getCellArray().length, myScrollingGrid.getCellArray()[0].length};
        return lengthArray;
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
