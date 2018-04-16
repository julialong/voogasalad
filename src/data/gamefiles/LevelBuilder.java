package data.gamefiles;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import data.serialization.Serializer;

public class LevelBuilder {

	private static final String RESOURCE_FILE = "data.resources/gameObjects";
	private Map<String,Class<?>> objectTypes;
	private Serializer deserializer; 
	private File level;
	
	public LevelBuilder(File level) 
	{
		objectTypes= new HashMap<>();
		createObjectToClassMap();
		deserializer = new Serializer();
		
		this.level = level;
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
				e.printStackTrace();
			}
		}
	}
	
	
}
