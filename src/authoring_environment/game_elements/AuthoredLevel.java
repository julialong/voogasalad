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
        myLevel.setColor(Color.WHITE);
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
    // TODO: Remove Point from Scrolling Grid
    public GameEntity addObject(String ID, double x, double y, double cellSize) {
        GameEntity newEntity;
        Document objectDoc = getDocument(ID, ELEMENT_DATA_PATH);
        String path = objectDoc.getDocumentElement().getAttribute("ImageFile");
        String type = objectDoc.getDocumentElement().getAttribute("GameEntity");
        String behavior = objectDoc.getDocumentElement().getAttribute("Behavior");
        String interaction = objectDoc.getDocumentElement().getAttribute("Interaction");
        String movement = objectDoc.getDocumentElement().getAttribute("Movement");
        String powerup = objectDoc.getDocumentElement().getAttribute("PowerUp");
        String projectile = objectDoc.getDocumentElement().getAttribute("Projectile");
        String weapon = objectDoc.getDocumentElement().getAttribute("Weapon");
        int xSize;
        int ySize;
        try {
            xSize = Integer.parseInt(objectDoc.getDocumentElement().getAttribute("XDimension"));
            ySize = Integer.parseInt(objectDoc.getDocumentElement().getAttribute("YDimension"));
        }
        catch (Exception e) {
            xSize = 1;
            ySize = 1;
        }

        newEntity = createObject(type, x * cellSize, y * cellSize);
        if (newEntity == null) {
            return null;
        }
        newEntity.setImagePath(path);
        createBehavior(behavior, newEntity);
        newEntity.setMovementType(createMovement(movement));
        newEntity.addInteraction(createInteraction(interaction));
        // newEntity.addPowerUp(createPowerUp(powerup));
        newEntity.setSizeX(xSize * cellSize);
        newEntity.setSizeY(ySize * cellSize);
        myLevel.addObject(newEntity);
        System.out.println("size: " + newEntity.getSizeX() + ", " + newEntity.getSizeY());
        System.out.println("location: " + newEntity.getKinematics().getX() + ", " + newEntity.getKinematics().getY());
        return newEntity;
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

    private void createBehavior(String behavior, GameEntity entity) {
        try {
            Constructor<?> behaviorConstructor = Class.forName(ENTITY_PATH + behavior).getConstructor(GameEntity.class);
            behaviorConstructor.setAccessible(true);
            Behavior newBehavior = (Behavior) behaviorConstructor.newInstance(entity);
            entity.addBehavior(newBehavior);
        }
        catch (Exception e) {
            // TODO: handle this
        }
    }

    private Movement createMovement(String movement) {
        try {
            Constructor<?> movementConstructor = Class.forName(ENTITY_PATH + movement).getConstructor();
            movementConstructor.setAccessible(true);
            return (Movement) movementConstructor.newInstance();
        }
        catch (Exception e) {
            // TODO: handle this
            return null;
        }
    }

    private Interaction createInteraction(String interaction) {
        try {
            Constructor<?> interactionConstructor = Class.forName(ENTITY_PATH + interaction).getConstructor();
            interactionConstructor.setAccessible(true);
            return (Interaction) interactionConstructor.newInstance();
        }
        catch (Exception e) {
            // TODO: handle this
            return null;
        }
    }

    private PowerUp createPowerUp(String powerup) {
        try {
            Constructor<?> powerupConstructor = Class.forName(ENTITY_PATH + powerup).getConstructor();
            powerupConstructor.setAccessible(true);
            return (PowerUp) powerupConstructor.newInstance();
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
