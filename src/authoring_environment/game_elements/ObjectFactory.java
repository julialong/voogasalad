package authoring_environment.game_elements;

import authoring_environment.DocumentGetter;
import engine.behavior.Behavior;
import engine.entity.Enemy;
import engine.entity.GameEntity;
import engine.entity.Player;
import engine.interaction.Interaction;
import engine.level.Level;
import engine.movement.Movement;
import engine.powerup.PowerUp;
import engine.weapon.Weapon;
import org.w3c.dom.Document;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The object factory of a certain class creates the objects needed and adds it to that class
 *
 * @author Julia Long
 * Date started: April 29 18
 */
public class ObjectFactory implements DocumentGetter {

    private Level myLevel;
    private Document myDocument;
    private GameEntity newEntity;

    private static final String ENTITY_PATH = "engine.entity.";
    private static final String BEHAVIOR_PATH = "engine.behavior.";
    private static final String MOVEMENT_PATH = "engine.movement.";
    private static final String INTERACTION_PATH = "engine.interaction.";
	private static final String ELEMENT_DATA_PATH = "./data/authoredElementData/";

    public ObjectFactory(Level level) {
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
        myDocument = getDocument(ID, ELEMENT_DATA_PATH);
        String path = getImagePath(myDocument);
        String type = myDocument.getDocumentElement().getAttribute("GameEntity");
        List<String> behavior = new ArrayList<String>();
		List<String> interaction = new ArrayList<String>();
        if(! (type.equals("Player"))) {
        		behavior = getBehaviors(myDocument);
        		interaction = getInteractions(myDocument);
        		
        }
        String movement = getMovement(myDocument);
        String weapon = getWeapon(myDocument);
        int xSize;
        int ySize;
        try {
            xSize = getXDimension(myDocument);
            ySize = getYDimension(myDocument);
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
        newEntity.setMovementType(createMovement(movement));
        makeBehaviors(newEntity, behavior);
		makeInteractions(newEntity, interaction);

        //setWeapon(weapon);
        newEntity.setSizeX(xSize * cellSize);
        newEntity.setSizeY(ySize * cellSize);
        newEntity.setFrictionConstant(Double.parseDouble(getBasicAttribute(myDocument, "Friction")));
        newEntity.setJumpFactor(Double.parseDouble(getBasicAttribute(myDocument, "JumpFactor")));
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
    		List<String> behaviorAttributes = getBehaviorAttributes(myDocument, behavior);
        try {
            Constructor<?> behaviorConstructor = Class.forName(BEHAVIOR_PATH + behavior).getConstructor(double.class, double.class);
            behaviorConstructor.setAccessible(true);
            Behavior b = (Behavior) behaviorConstructor.newInstance(Double.parseDouble(behaviorAttributes.get(0)),
                    Double.parseDouble(behaviorAttributes.get(1)));
            return (Behavior) behaviorConstructor.newInstance(Double.parseDouble(behaviorAttributes.get(0)),
                    Double.parseDouble(behaviorAttributes.get(1)));
        }
        catch (Exception e) {
            try {
                Constructor<?> behaviorConstructor = Class.forName(BEHAVIOR_PATH +  behavior).getConstructor(double.class);
                behaviorConstructor.setAccessible(true);
                return (Behavior) behaviorConstructor.newInstance(Double.parseDouble(behaviorAttributes.get(0)));
            }
            catch (Exception ee) {
                try {
                    Constructor<?> behaviorConstructor = Class.forName( BEHAVIOR_PATH + behavior).getConstructor();
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
            Constructor<?> movementConstructor = Class.forName(MOVEMENT_PATH + movement).getConstructor();
            movementConstructor.setAccessible(true);
            return (Movement) movementConstructor.newInstance();
        }
        catch (Exception e) {
            return null;
        }
    }

    private void makeInteractions(GameEntity newEntity, List<String> interactions) {
        for (String interaction : interactions) {
            newEntity.addInteraction(createInteraction(interaction));
        }
    }

    private Interaction createInteraction(String interaction) {
        List<String> interactionAttributes = getInteractionAttributes(myDocument, interaction);
        try {
            Constructor<?> interactionConstructor = Class.forName(INTERACTION_PATH + interaction).getConstructor(PowerUp.class);
            interactionConstructor.setAccessible(true);
            return (Interaction) interactionConstructor.newInstance(createPowerUp(interactionAttributes.get(0)));
        }
        catch (Exception e) {
            try {
                Constructor<?> interactionConstructor = Class.forName(INTERACTION_PATH + interaction).getConstructor();
                interactionConstructor.setAccessible(true);
                return (Interaction) interactionConstructor.newInstance();
            }
            catch (Exception ee) {
                return null;
            }
        }
    }

    private PowerUp createPowerUp(String powerup) {
        List<String> powerupAttributes = getPowerupAttributes(myDocument, powerup);
        try {
            Constructor<?> powerupConstructor = Class.forName(ENTITY_PATH + powerup).getConstructor(Weapon.class);
            powerupConstructor.setAccessible(true);
            return (PowerUp) powerupConstructor.newInstance(createWeapon(powerupAttributes.get(0)));
        }
        catch (Exception e) {
            try {
                Constructor<?> powerupConstructor = Class.forName(ENTITY_PATH + powerup).getConstructor(double.class);
                powerupConstructor.setAccessible(true);
                return (PowerUp) powerupConstructor.newInstance(Double.parseDouble(powerupAttributes.get(0)));
            }
            catch (Exception ee) {
                try {
                    Constructor<?> powerupConstructor = Class.forName(ENTITY_PATH + powerup).getConstructor();
                    powerupConstructor.setAccessible(true);
                    return (PowerUp) powerupConstructor.newInstance();
                }
                catch (Exception eee) {
                    return null;
                }
            }
        }
    }

    private void setWeapon(String weapon) {
        if (newEntity instanceof Player) {
            Player newPlayer = (Player) newEntity;
            newPlayer.setWeapon(createWeapon(weapon));
        }
        if (newEntity instanceof Enemy) {
            Enemy newEnemy = (Enemy) newEntity;
            newEnemy.setWeapon(createWeapon(weapon));
        }
    }

    private Weapon createWeapon(String weapon) {
        try {
            Constructor<?> weaponConstructor = Class.forName(ENTITY_PATH + weapon).getConstructor(GameEntity.class, Level.class);
            weaponConstructor.setAccessible(true);
            return (Weapon) weaponConstructor.newInstance(newEntity, myLevel);
        }
        catch (Exception e) {
            return null;
        }
    }

}
