package data.levelBuilders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import data.resources.DataFileException;
import data.serialization.Serializer;
import engine.behavior.Behavior;
import engine.behavior.MoveForward;
import engine.entity.Foes;
import engine.entity.GameEntity;
import engine.entity.Player;
import engine.level.BasicLevel;
import engine.level.Level;

/**
 * Creates levels to return to the Game Player when a 
 * game is going to be played
 * 
 * @author Belanie Nagiel
 *
 */
public class LevelBuilder {

	private static final String RESOURCE_FILE = "data.resources/gameObjects";
	private static final String BEHAVIOR_SKIPS = "data.resources/behaviorsToSkip";
	private static final String NAME = "name";
	private static final String COLOR = "color";
	private Map<String,Class<?>> objectTypes;
	private List<String> behaviorsToSkip;
	private Serializer deserializer; 
	private File levelFile;
	private Player player;
	
	/**
	 * Class Constructor
	 * 
	 * Takes in a level file and creates the object to class map for all of the
	 * GameObjects
	 * 
	 * @param level
	 * @throws DataFileException 
	 */
	public LevelBuilder(File level) throws DataFileException 
	{
		objectTypes= new HashMap<>();
		createObjectToClassMap();
		behaviorsToSkip = new ArrayList<>();
		buildBehaviorSkipMap();
		deserializer = new Serializer();
		
		levelFile = level;
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
				System.out.println(objectName);
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
		BasicLevel level = new BasicLevel();
		try 
		{
			JsonParser jsonParser = new JsonParser();
			JsonElement jelement = jsonParser.parse(new FileReader(levelFile));
			JsonObject jobject = jelement.getAsJsonObject();
			addMetaData(level, jobject);
			addGameObjects(level,jobject);
		}
		catch(JsonIOException | JsonSyntaxException | FileNotFoundException e)
		{
			throw new DataFileException("Could not find the file to load for Level", e);
		}
		return level;	
	}
	
	/**
	 * Adds the relevant instance variables to the level
	 * 
	 * @param level
	 * @param jobject
	 */
	private void addMetaData(Level level, JsonObject jobject)
	{
		String levelName = jobject.get(NAME).getAsString();
//		Color color = Color.web(jobject.get(COLOR).getAsString());
		
		level.setName(levelName);
//		level.setColor(color);
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
//			System.out.println("JArray Item " + jarray.get(i).getAsJsonObject());
			GameEntity ge = (GameEntity) convertToObject(jarray.get(i).getAsJsonObject(), objectType);
			checkPlayer(ge);
			checkFoe(ge);
			newObjectsOfType.add(ge);
		}
		return newObjectsOfType;
	}

	private void checkPlayer(GameEntity ge) 
	{
//		System.out.println("CHECKING THE PLAYER " + ge.getClass());
		if(ge.getClass().equals(Player.class))
		{
//			System.out.println("HERE OMG: " + ge);
			player = (Player) ge;
		}
	}
	
	private void checkFoe(GameEntity ge) 
	{
//		System.out.println("CHECKING THE FOE " + ge.getClass());
		if(ge.getClass().equals(Foes.class))
		{
			System.out.println("IM DOING THE THING AND SETTING IT");
			for(Behavior b: ((Foes)ge).getBehaviorList())
			{
				System.out.println("this thing" + b.getClass().toString());
				System.out.println(behaviorsToSkip.contains(b.getClass().toString().split(" ")[1]));
				if(behaviorsToSkip.contains(b.getClass().toString().split(" ")[1]))
				{
					b = getBehavior(b.getClass().toString().split(" ")[1]);
				}
			}
		}
	}
	
	private Behavior getBehavior(String behaviorType) {
		try 
		{
			Class behaviorClass = Class.forName(behaviorType);
			Constructor<?> c = behaviorClass.getConstructor(Player.class);
			c.setAccessible(true);
			Object o = c.newInstance(player);
			System.out.println(o);
			return (Behavior)o;
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) 
		{
			throw new JsonParseException("Could not create behavior that contains a player");
		}
		
	}
	
	private void buildBehaviorSkipMap()
	{
		
		ResourceBundle behaviors = ResourceBundle.getBundle(BEHAVIOR_SKIPS);
		Enumeration<String> behaviorNames = behaviors.getKeys();
		while(behaviorNames.hasMoreElements())
		{
			String behaviorName = behaviorNames.nextElement();
			behaviorsToSkip.add(behaviors.getString(behaviorName));
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
//		System.out.println();
//		System.out.println("toConvert " + toConvert + "objectType " + objectType);
		return deserializer.deserialize(toConvert.toString(), objectTypes.get(objectType));
	}
}
