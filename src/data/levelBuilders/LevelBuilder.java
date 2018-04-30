package data.levelBuilders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import data.resources.DataFileException;
import data.serialization.BehaviorSkipManager;
import data.serialization.Serializer;
import engine.behavior.Behavior;
import engine.entity.Enemy;
import engine.entity.GameEntity;
import engine.entity.Player;
import engine.level.BasicLevel;
import engine.level.Level;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

/**
 * Creates levels to return to the Game Player when a
 * game is going to be played
 *
 * @author Belanie Nagiel
 *
 */
public class LevelBuilder {

	private static final String RESOURCE_FILE = "data.resources/gameObjects";
	private static final String NAME = "name";
	private static final String COLOR = "color";
	private static final String WIDTH = "width";
	private static final String HEIGHT = "height";
	private static final int CAMERA_WIDTH = 1100;
	private Map<String,Class<?>> objectTypes;
	private List<String> behaviorsToSkip;
	private Serializer deserializer; 
	private File levelFile;
	private Player player;
	private BehaviorSkipManager skipManager;
	private double levelWidth;
	private double levelHeight;
	private boolean translate;
	
	/**
	 * Class Constructor
	 *
	 * Takes in a level file and creates the object to class map for all of the
	 * GameObjects
	 *
	 * @param level
	 * @throws DataFileException
	 */
	public LevelBuilder(File level, Boolean translate) throws DataFileException 
	{
		objectTypes= new HashMap<>();
		createObjectToClassMap();

		
		skipManager = new BehaviorSkipManager();
		behaviorsToSkip = skipManager.getBehaviorsToSkip();
		
		deserializer = new Serializer();
		levelFile = level;
		
		this.translate = translate;
	}

	/**
	 * Reads in a properties file of game objects to their appropriate classes
	 * in order to make a map for later deserialization.
	 * @throws DataFileException
	 *
	 */
	private void createObjectToClassMap() throws DataFileException
	{
		ResourceBundle gameObjects = ResourceBundle.getBundle(RESOURCE_FILE);
		Enumeration<String> objectNames = gameObjects.getKeys();
		while(objectNames.hasMoreElements())
		{
			String objectName = objectNames.nextElement();
			try
			{
				Class<?> objectClass = Class.forName(gameObjects.getString(objectName));
				objectTypes.put(objectName, objectClass);
			}
			catch (ClassNotFoundException e)
			{
				throw new DataFileException("Could not find object class for reflection",e);
			}
		}
	}

	/**
	 * Returns a new BasicLevel with the appropriate data and
	 * game objects.
	 *
	 * @return
	 * @throws DataFileException
	 */
	public Level buildLevel() throws DataFileException
	{
		try 
		{
			JsonParser jsonParser = new JsonParser();
			JsonElement jelement = jsonParser.parse(new FileReader(levelFile));
			JsonObject jobject = jelement.getAsJsonObject();
			BasicLevel level = addMetaData(jobject);
			addGameObjects(level,jobject);
			return level;
		}
		catch(JsonIOException | JsonSyntaxException | FileNotFoundException e)
		{
			throw new DataFileException("Could not find the file to load for Level", e);
		}
	}

	/**
	 * Adds the relevant instance variables to the level
	 * 
	 * @param jobject
	 * @return 
	 */
	private BasicLevel addMetaData(JsonObject jobject)
	{
		String levelName = jobject.get(NAME).getAsString();

		Color color = Color.web(jobject.get(COLOR).getAsString());
		levelWidth = jobject.get(WIDTH).getAsDouble();
		levelHeight = jobject.get(HEIGHT).getAsDouble();
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		int camWidth = CAMERA_WIDTH;
		int camHeight = (int) ((primaryScreenBounds.getHeight() / primaryScreenBounds.getWidth()) * camWidth);
		
		BasicLevel level = new BasicLevel((int)levelWidth, (int)levelHeight, camWidth, camHeight);
		level.setName(levelName);
		level.setColor(color);
		
		return level;
	}

	/**
	 * Adds the game objects to the level
	 *
	 * @param level
	 * @param jobject
	 */
	private void addGameObjects(Level level, JsonObject jobject)
	{
		List<GameEntity> gameObjects = new ArrayList<>();

		for(String objectType: objectTypes.keySet())
		{
			if(jobject.has(objectType))
			{
				gameObjects.addAll(retrieveObjectsOfType(jobject, objectType));
			}
		}
		level.setObjects(gameObjects);
	}

	/**
	 * Returns a list of game objects associated with the given object type
	 *
	 * @param jobject
	 * @param objectType
	 * @return
	 */
	private List<GameEntity> retrieveObjectsOfType(JsonObject jobject, String objectType) {
		List<GameEntity> newObjectsOfType = new ArrayList<>();
		JsonArray jarray = jobject.getAsJsonArray(objectType);
		for(int i = 0; i < jarray.size(); i++)
		{
			GameEntity ge = (GameEntity) convertToObject(jarray.get(i).getAsJsonObject(), objectType);
			checkPlayer(ge);
			checkFoe(ge);
			if(translate) {translateCoordinates(ge);}
			newObjectsOfType.add(ge);
		}
		return newObjectsOfType;
	}
	
	/**
	 * Applies the translation to convert game authoring coordinates to game engine
	 * compatible coordinates.
	 * 
	 * @param ge
	 */
	private void translateCoordinates(GameEntity ge)
	{
		double translatedX = ge.getPosition()[0] - levelWidth/2;
		double translatedY = (levelHeight/2) - ge.getPosition()[1];

		ge.overridePosition(translatedX, translatedY);
	}

	/**
	 * Sets the player in case of use in populating Enemy behaviors that take in a player.
	 * 
	 * @param ge
	 */
	private void checkPlayer(GameEntity ge) 
	{
		if(ge.getClass().equals(Player.class))
		{
			player = (Player) ge;
		}
	}
	
	/**
	 * Checks to see if a behavior is of the type that needs a player and if so, populates
	 * that behavior with the player for the current game.
	 * 
	 * @param ge
	 */
	private void checkFoe(GameEntity ge) 
	{
		if(ge.getClass().equals(Enemy.class))
		{
			for(Behavior b: ((Enemy)ge).getBehaviorList())
			{
				if(behaviorsToSkip.contains(b.getClass().toString().split(" ")[1]))
				{
					b = skipManager.getBehavior(b.getClass().toString().split(" ")[1],player);
				}
			}
		}
	}

	/**
	 * Given the JSON object that will be converted into the game object
	 * and the type of the game object, returns the GameObject object for
	 * the JSON text.
	 *
	 * @param toConvert
	 * @param objectType
	 * @return
	 */
	private Object convertToObject(JsonObject toConvert, String objectType)
	{
		return deserializer.deserialize(toConvert.toString(), objectTypes.get(objectType));
	}
}
