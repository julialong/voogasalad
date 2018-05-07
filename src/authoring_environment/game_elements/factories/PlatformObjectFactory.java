package authoring_environment.game_elements.factories;

import authoring_environment.DocumentGetter;
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

/**
 * The PlatformObjectFactory is the implementation of the ObjectFactory for a platform
 * game.
 *
 * @author Julia Long
 * Date started: April 29 18
 */
public class PlatformObjectFactory implements ObjectFactory, DocumentGetter {

    private Level myLevel;
    private Document myDocument;
    private GameEntity newEntity;

    private static final String GAME_ENTITY = "GameEntity";
    private static final String PLAYER = "Player";
    private static final String FRICTION = "Friction";
    private static final String JUMP_FACTOR = "JumpFactor";
    private static final String ENTITY_PATH = "engine.entity.";
    private static final String MOVEMENT_PATH = "engine.movement.";
	private static final String ELEMENT_DATA_PATH = "./data/authoredElementData/";

    public PlatformObjectFactory(Level level) {
        myLevel = level;
    }

    /**
     * Adds constructed GameEntity to Level at the correct location in the level
     * @param ID is the String ID of the new object
     * @param x is the x position
     * @param y is the y position
     * @param cellSize is the size of the gridcell
     * @return the constructed GameEntity
     */
    @Override
    public GameEntity addObject(String ID, double x, double y, double cellSize) {
        myDocument = getDocument(ID, ELEMENT_DATA_PATH);
        String path = getImagePath(myDocument);
        String type = myDocument.getDocumentElement().getAttribute(GAME_ENTITY);
        List<String> behavior = new ArrayList<>();
		List<String> interaction = new ArrayList<>();
        if(! (type.equals(PLAYER))) {
            behavior = getBehaviors(myDocument);
            interaction = getInteractions(myDocument);
        }
        String movement = getMovement(myDocument);
        newEntity = createObject(type, x * cellSize, y * cellSize);
        if (newEntity == null) {
            return null;
        }
        setObjectAttributes(path, movement, behavior, interaction);
        setPhysicalAttributes(getXDimension(myDocument), getYDimension(myDocument), cellSize);
        myLevel.addObject(newEntity);
        return newEntity;
    }

    // adds Object attributes to the newEntity
    private void setObjectAttributes(String path, String movement, List<String> behavior, List<String> interaction) {
        newEntity.setImagePath(path);
        newEntity.setMovementType(createMovement(movement));
        makeBehaviors(newEntity, behavior);
        makeInteractions(newEntity, interaction);
    }

    // add physical attributes to the newEntity
    private void setPhysicalAttributes(double xSize, double ySize, double cellSize) {
        newEntity.setSizeX(xSize * cellSize);
        newEntity.setSizeY(ySize * cellSize);
        newEntity.setFrictionConstant(Double.parseDouble(getBasicAttribute(myDocument, FRICTION)));
        newEntity.setJumpFactor(Double.parseDouble(getBasicAttribute(myDocument, JUMP_FACTOR)));
    }

    // create a GameEntity object using reflection
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

    // using a BehaviorFactory, adds correct behaviors to the newEntity
    private void makeBehaviors(GameEntity newEntity, List<String> behaviors) {
        BehaviorFactory behaviorFactory = new BehaviorFactory();
        for (String behavior : behaviors) {
            newEntity.addBehavior(behaviorFactory.createBehavior(behavior, getBehaviorAttributes(myDocument, behavior)));
        }
    }

    // creates a Movement object to add to the newEntity using reflection
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

    // using a InteractionFactory, adds correct interactions to the newEntity
    private void makeInteractions(GameEntity newEntity, List<String> interactions) {
        InteractionFactory interactionFactory = new InteractionFactory();
        for (String interaction : interactions) {
            newEntity.addInteraction(interactionFactory.createInteraction(interaction, getInteractionAttributes(myDocument, interaction),
                                                                            myDocument, newEntity, myLevel));
        }
    }
}
