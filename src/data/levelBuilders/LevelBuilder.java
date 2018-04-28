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
import data.serialization.Serializer;
import engine.behavior.Behavior;
import engine.behavior.MoveForward;
import engine.entity.Foes;
import engine.entity.GameEntity;
import engine.entity.Player;
import engine.level.BasicLevel;
import engine.level.Level;
import javafx.scene.paint.Color;

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
	private Map<String,Class<?>> objectTypes;
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
			System.out.println("JArray Item " + jarray.get(i).getAsJsonObject());
			GameEntity ge = (GameEntity) convertToObject(jarray.get(i).getAsJsonObject(), objectType);
			if(ge.getClass().equals(Player.class))
			{
				System.out.println("HERE OMG: " + ge);
				player = (Player) ge;
			}
			if(ge.getClass().equals(Foes.class))
			{
				for(Behavior b: ((Foes)ge).getBehaviorList())
				{
					if(b.getClass().equals(MoveForward.class))
					{
						System.out.println("IS THERE ACTUALLY EVER A MOVE FORWARD LMK");
						((MoveForward)b).setPlayer(player);
					}
				}
////				MoveForward mf = (MoveForward) ge;
//				ge = (GameEntity) new MoveForward(player);
			}
			newObjectsOfType.add(ge);
		}
		return newObjectsOfType;
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
		System.out.println();
		System.out.println("toConvert " + toConvert + "objectType " + objectType);
		return deserializer.deserialize(toConvert.toString(), objectTypes.get(objectType));
	}
}
