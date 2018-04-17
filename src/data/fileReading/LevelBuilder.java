package data.fileReading;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import data.serialization.Serializer;
import engine.entity.GameEntity;
import engine.level.BasicLevel;
import engine.level.Level;

public class LevelBuilder {

	private static final String RESOURCE_FILE = "data.resources/gameObjects";
	private Map<String,Class<?>> objectTypes;
	private Serializer deserializer; 
	private File levelFile;
	
	/**
	 * Class Constructor
	 * 
	 * Takes in a level file and creates the object to class map for all of the
	 * GameObjects
	 * 
	 * @param level
	 */
	public LevelBuilder(File level) 
	{
		objectTypes= new HashMap<>();
		createObjectToClassMap();
		deserializer = new Serializer();
		
		levelFile = level;
	}
	
	/**
	 * Reads in a properties file of game objects to their appropriate classes
	 * in order to make a map for later deserialization.
	 * 
	 */
	private void createObjectToClassMap()
	{
		ResourceBundle gameObjects = ResourceBundle.getBundle(RESOURCE_FILE);
		Enumeration<String> objectNames = gameObjects.getKeys();
		while(objectNames.hasMoreElements())
		{
			String objectName = objectNames.nextElement();
			try {
				Class<?> objectClass = Class.forName(gameObjects.getString(objectName));
				objectTypes.put(objectName, objectClass);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(new JFrame(),
						 "Could not find object class for reflection",
						    "Class Not Found Exception",
					    JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Returns a new BasicLevel with the appropriate data and 
	 * game objects.
	 * 
	 * @return
	 */
	public Level buildLevel()
	{
		BasicLevel level = new BasicLevel();
		try 
		{
			JsonParser jsonParser = new JsonParser();
			JsonObject jobject;
			jobject = jsonParser.parse(new FileReader(levelFile)).getAsJsonObject();
			addMetaData(level, jobject);
			addGameObjects(level,jobject);
		}
		catch(JsonIOException | JsonSyntaxException | FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(new JFrame(),
					 "Could not find the file to load for Level",
					    "File Reader Exception",
				    JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
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
		String levelName = jobject.get("name").getAsString();
		int id = jobject.get("id").getAsInt();
		
		level.setName(levelName);
		level.setID(id);
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
				JsonArray jarray = jobject.getAsJsonArray(objectType);
				for(int i = 0; i < jarray.size(); i++)
				{
					gameObjects.add((GameEntity) convertToObject(jarray.get(i).getAsJsonObject(), objectType));
				}
			}
		}
		level.setObjects(gameObjects);
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
		JsonObject j = toConvert;
		Object converted = deserializer.deserialize(j.toString(), objectTypes.get(objectType));
		return converted;
	}
	
}
