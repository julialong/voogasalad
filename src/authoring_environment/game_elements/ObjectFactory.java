package authoring_environment.game_elements;

import authoring_environment.DocumentGetter;
import engine.behavior.Behavior;
import engine.entity.GameEntity;
import engine.interaction.Interaction;
import engine.level.Level;
import engine.movement.Movement;
import engine.powerup.PowerUp;
import engine.weapon.Weapon;
import org.w3c.dom.Document;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * The object factory of a certain class creates the objects needed and adds it to that class
 *
 * @author Julia Long
 * Date started: April 29 18
 */
public class ObjectFactory implements DocumentGetter {

    private Level myLevel;

    private static final String ENTITY_PATH = "engine.entity.";
    private static final String ELEMENT_DATA_PATH = "./data/authoredElementData/";

    ObjectFactory(Level level) {
        myLevel = level;
    }

    /**
     * Adds constructed GameEntity to Level
     * @param ID is the String ID of the new object
     * @param x is the x position
     * @param y is the y position
     * @param cellSize is the size of the gridcell
     * @return the constructed GameEntity
     */
    public GameEntity addObject(String ID, double x, double y, double cellSize) {
        GameEntity newEntity;
        Document objectDoc = getDocument(ID, ELEMENT_DATA_PATH);
        String path = getImagePath(objectDoc);
        String type = objectDoc.getDocumentElement().getAttribute("GameEntity");
        List<String> behavior = getBehaviors(objectDoc);
        List<String> interaction = getInteractions(objectDoc);
        String movement = getMovement(objectDoc);
        int xSize;
        int ySize;
        try {
            xSize = getXDimension(objectDoc);
            ySize = getYDimension(objectDoc);
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
        makeBehaviors(newEntity, behavior);
        newEntity.setMovementType(createMovement(movement));
        makeInteractions(newEntity, interaction);
        newEntity.setSizeX(xSize * cellSize);
        newEntity.setSizeY(ySize * cellSize);
        myLevel.addObject(newEntity);
        return newEntity;
    }

    private GameEntity createObject(String type, double x, double y) {
        try {
            Constructor<?> objectConstructor = Class.forName(ENTITY_PATH + type).getConstructor(Double.TYPE, Double.TYPE);
            objectConstructor.setAccessible(true);
            return ((GameEntity) objectConstructor.newInstance(x, y));
        }
        catch (Exception e) {
            return null;
        }
    }

    private void makeBehaviors(GameEntity newEntity, List<String> behaviors) {
        for (String behavior : behaviors) {
            newEntity.addBehavior(createBehavior(behavior));
        }
    }

    private Behavior createBehavior(String behavior) {
        try {
            Constructor<?> behaviorConstructor = Class.forName(ENTITY_PATH + behavior).getConstructor(Double.class, Double.class);
            behaviorConstructor.setAccessible(true);
            return (Behavior) behaviorConstructor.newInstance();
        }
        catch (Exception e) {
            try {
                Constructor<?> behaviorConstructor = Class.forName(ENTITY_PATH + behavior).getConstructor(Double.class);
                behaviorConstructor.setAccessible(true);
                return (Behavior) behaviorConstructor.newInstance();
            }
            catch (Exception ee) {
                try {
                    Constructor<?> behaviorConstructor = Class.forName(ENTITY_PATH + behavior).getConstructor();
                    behaviorConstructor.setAccessible(true);
                    return (Behavior) behaviorConstructor.newInstance();
                }
                catch (Exception eee) {
                    return null;
                }
            }
        }
    }

    private Movement createMovement(String movement) {
        try {
            Constructor<?> movementConstructor = Class.forName(ENTITY_PATH + movement).getConstructor();
            movementConstructor.setAccessible(true);
            return (Movement) movementConstructor.newInstance();
        }
        catch (Exception e) {
            return null;
        }
    }

    private void makeInteractions(GameEntity newEntity, List<String> interactions) {
        for (String interaction : interactions) {
            newEntity.addInteraction(createInteraction(interaction, null));
        }
    }

    private Interaction createInteraction(String interaction, String powerup) {
        try {
            Constructor<?> interactionConstructor = Class.forName(ENTITY_PATH + interaction).getConstructor(PowerUp.class);
            interactionConstructor.setAccessible(true);
            return (Interaction) interactionConstructor.newInstance(createPowerUp(powerup));
        }
        catch (Exception e) {
            try {
                Constructor<?> interactionConstructor = Class.forName(ENTITY_PATH + interaction).getConstructor();
                interactionConstructor.setAccessible(true);
                return (Interaction) interactionConstructor.newInstance();
            }
            catch (Exception ee) {
                return null;
            }
        }
    }

    private PowerUp createPowerUp(String powerup) {
        try {
            Constructor<?> powerupConstructor = Class.forName(ENTITY_PATH + powerup).getConstructor();
            powerupConstructor.setAccessible(true);
            return (PowerUp) powerupConstructor.newInstance();
        }
        catch (Exception e) {
            return null;
        }
    }

    private Weapon createWeapon(String weapon) {
        try {
            Constructor<?> weaponConstructor = Class.forName(ENTITY_PATH + weapon).getConstructor();
            weaponConstructor.setAccessible(true);
            return (Weapon) weaponConstructor.newInstance();
        }
        catch (Exception e) {
            return null;
        }
    }

}
