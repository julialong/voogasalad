package data.gamefiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import data.serialization.Serializer;

/**
 * @author Belanie Nagiel
 * 
 * Class that facilitates the loading of games in the form of 
 * lists of game objects. Can load an entire game as a map of levels 
 * to game objects or can load one specific level or just the
 * settings.
 *
 */
public class GameFileReader implements JSONtoObject {

	private final String gameFolder = "./data/gameData";
	private String gameDirectory;
	private File currentGame;
	private File currentLevel;
	private Map<String,Class<?>> objectTypes;
	private Serializer deserializer; 
	
	/**
	 * Class Constructor.
	 * Instantiates the game object to class map and the deserializer
	 */
	public GameFileReader()
	{
		objectTypes= new HashMap<>();
		createObjectToClassMap();
		deserializer = new Serializer(); //ORIGINAL IS DESERIALIZER
	}
	
	/**
	 * Reads in a properties file of game objects to their appropriate classes
	 * in order to make a map for later deserialization.
	 * 
	 */
	private void createObjectToClassMap()
	{
		ResourceBundle gameObjects = ResourceBundle.getBundle("data.resources/gameObjects");
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
	
	/**
	 * Sets the currentGame File object
	 * 
	 * @param gameName
	 */
	private void retrieveCurrentGame(String gameName)
	{
		gameDirectory = gameFolder + "/" + gameName;
		currentGame = new File(gameDirectory); 
	}
	
	/**
	 * Sets the currentLevel File object
	 * @param gameName
	 * @param level
	 */
	private void retrieveLevel(String gameName, String level)
	{
		retrieveCurrentGame(gameName);
//		currentLevel = new File(gameDirectory + "/" + level);
		currentLevel = new File(gameDirectory + "/" + "Default.json");
	}
	
	@Override
	/**
	 * Returns a map of levels/settings to their associated game objects based
	 * on the gameName and the json files for that game.
	 * 
	 * @param gameName
	 * @return 
	 */
	public Map<String, List<Object>> loadCompleteGame(String gameName) {
		Map<String, List<Object>> completeGame = new HashMap<>();
		retrieveCurrentGame(gameName);
		File[] gameFiles = currentGame.listFiles();
		int i = 1;
		for(File gameFile: gameFiles)
		{
			if(gameFile.toString().contains("Settings"))
			{
				//TO DO: figure out how to represent settings for a game
			}
			else
			{
				completeGame.put(Integer.toString(i), loadLevel(gameName, i));
				i++;
			}
		}
		return completeGame;
	}
	
	@Override
	/**
	 * Returns the list of objects for a specific level of a game based on the JSON
	 * file for that level.
	 * 
	 * @param gameName
	 * @param levelNumber
	 * @return
	 */
	public List<Object> loadLevel(String gameName, int levelNumber) {
		retrieveLevel(gameName, Integer.toString(levelNumber));
		List<Object> gameObjects = new ArrayList<>();
		try 
		{
			JsonParser jsonParser = new JsonParser();
			JsonElement jelement = jsonParser.parse(new FileReader(currentLevel));
			JsonObject  jobject = jelement.getAsJsonObject();
			for(String objectType: objectTypes.keySet())
			{
				if(jobject.has(objectType))
				{
					JsonArray jarray = jobject.getAsJsonArray(objectType);
					for(int i = 0; i < jarray.size(); i++)
					{
						gameObjects.add(convertToObject(jarray.get(i).getAsJsonObject(), objectType));
					}
				}
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return gameObjects;
	}


	@Override
	/**
	 * To Do: Returns the list of objects for the general game settings
	 * 
	 * @param gameName
	 * @return
	 */
	public Map<String,String> loadSettings(String gameName) {
		// TODO Auto-generated method stub
		Map<String,String> settingsDetails = new HashMap<>();
		File settings = retrieveSettings(gameName);
		try {
			JsonParser jsonParser = new JsonParser();
			JsonElement jelement = jsonParser.parse(new FileReader(settings));
			JsonObject  jobject = jelement.getAsJsonObject();
			String description = jobject.get("description").toString();
			settingsDetails.put("description", description);
			String ready = jobject.get("ready").toString();
			settingsDetails.put("ready", ready);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return settingsDetails;
	}
	
	private File retrieveSettings(String gameName) {
		retrieveCurrentGame(gameName);
		return new File(gameDirectory + "/Settings.json");
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

	@Override
	public Map<String,String> getGameNames() {
		Map<String,String> gameNames = new HashMap<>();
		File gamesDirectory = new File(gameFolder);
		File[] games= gamesDirectory.listFiles();
		for(File game: games)
		{
			int index = game.toString().lastIndexOf("/") + 1;
			String gameName = game.toString().substring(index).trim();
			Map<String,String> gameSettings = loadSettings(gameName);
			if(gameSettings.get("ready").equals("true"))
			{
				gameNames.put(gameName, gameSettings.get("description"));
			}
		}
		return gameNames;
	}
}


