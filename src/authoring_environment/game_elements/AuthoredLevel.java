package authoring_environment.game_elements;

import authoring_environment.DocumentGetter;
import authoring_environment.grid.ScrollingGrid;
import engine.behavior.Behavior;
import engine.entity.GameEntity;
import engine.level.Level;
import javafx.scene.layout.Background;
import org.w3c.dom.Document;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


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

    private static final String ENTITY_PATH = "engine.entity.";
    private static final String ELEMENT_DATA_PATH = "./data/authoredElementData/";

    /**
     * Creates a new authored level
     * @param level is the level
     * @param scrollingGrid is the scrolling grid
     */
    public AuthoredLevel(Level level, ScrollingGrid scrollingGrid) {
        myLevel = level;
        myScrollingGrid = scrollingGrid;
        myScrollingGrid.setMediator(this);
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

    /**
     * Sets the new size of the level
     * @param x
     * @param y
     */
    public void setSize(double x, double y) {
        // TODO: set size of level
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
    // TODO: Remove Point from Scrolling Grid
    public void addObject(String ID, double x, double y) {
        GameEntity newEntity;
        Document objectDoc = getDocument(ID, ELEMENT_DATA_PATH);
        String path = objectDoc.getDocumentElement().getAttribute("ImageFile");
        String type = objectDoc.getDocumentElement().getAttribute("GameEntity");
        String behavior = objectDoc.getDocumentElement().getAttribute("Behavior");
        String imagePath = objectDoc.getDocumentElement().getAttribute("ImageFile");
        String interaction = objectDoc.getDocumentElement().getAttribute("Interaction");
        String movement = objectDoc.getDocumentElement().getAttribute("Movement");
        String powerup = objectDoc.getDocumentElement().getAttribute("PowerUp");
        String projectile = objectDoc.getDocumentElement().getAttribute("Projectile");
        String weapon = objectDoc.getDocumentElement().getAttribute("Weapon");

        newEntity = createObject(type, x, y);
    }

    private GameEntity createObject(String type, double x, double y) {
        try {
            Constructor<?> objectConstructor = Class.forName(ENTITY_PATH + type).getConstructor(Double.TYPE, Double.TYPE);
            objectConstructor.setAccessible(true);
            return ((GameEntity) objectConstructor.newInstance(x, y));
        }
        catch (Exception e) {
            // TODO: handle this
            return null;
        }
    }

    private Behavior createBehavior(String behavior, GameEntity entity) {
        try {
            Constructor<?> behaviorConstructor = Class.forName(ENTITY_PATH + behavior).getConstructor(GameEntity.class);
            behaviorConstructor.setAccessible(true);
            return (Behavior) behaviorConstructor.newInstance(entity);
        }
        catch (Exception e) {
            // TODO: handle this
            return null;
        }
    }

    /**
     * Removes the object from the level
     * @param object is the object to remove
     */
    public void removeObject(GameEntity object){
        myLevel.getObjects().remove(object);
    }
}
