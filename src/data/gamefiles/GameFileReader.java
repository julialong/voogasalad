package data.gamefiles;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import authoring_environment.grid.ScrollingGrid;
import data.serialization.LevelSerializer;
import data.serialization.Serializer;
import engine.entity.GameEntity;
import engine.level.BasicLevel;
import engine.level.Level;

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

	private static final String GAME_FOLDER = "./data/gameData";
	private static final String JSON_EXTENSION = ".json";
	private static final String RESOURCE_FILE = "data.resources/gameObjects";
	private static final String SETTINGS = "Settings";
	private String NEST = "/";
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
		deserializer = new Serializer();

		if (System.getProperty("os.name").toString().contains("Windows"))	{
			NEST = "\\";
		}
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
	
	/**
	 * Sets the currentGame File object
	 * 
	 * @param gameName
	 */
	private void retrieveCurrentGame(String gameName)
	{
		gameDirectory = GAME_FOLDER + NEST + gameName;
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
		currentLevel = new File(gameDirectory + NEST + level + JSON_EXTENSION);
//		currentLevel = new File(gameDirectory + "/" + "Default.json");
	}
	
	@Override
	/**
	 * Returns a list of the Level objects for a game. Each level
	 * object is created from the information for each level in the game folder.
	 * 
	 * @param gameName
	 * @return 
	 */
	public List<Level> loadCompleteGame(String gameName) {
		List<Level> completeGame = new ArrayList<>();
		retrieveCurrentGame(gameName);
		File[] gameFiles = currentGame.listFiles();
		for(File gameFile: gameFiles)
		{
				int index = gameFile.toString().lastIndexOf(NEST) + 1;
				int endIndex = gameFile.toString().lastIndexOf(JSON_EXTENSION);
				String levelName = gameFile.toString().substring(index,endIndex).trim();
				if(levelName.equals(SETTINGS))
				{
					//TO DO: implementation for settings
				}
				else
				{
					completeGame.add(loadLevel(gameName, levelName));
				}		
		}
		return completeGame;
	}
	
	@Override
	/**
	 * Returns the Level object for a specific level of a game based on the JSON
	 * file for that level.
	 * 
	 * @param gameName
	 * @param levelNumber
	 * @return
	 */
	public Level loadLevel(String gameName, String name) {
		retrieveLevel(gameName, name);
		List<GameEntity> gameObjects = new ArrayList<>();
		BasicLevel level = new BasicLevel();
		try 
		{
			JsonParser jsonParser = new JsonParser();
			JsonElement jelement = jsonParser.parse(new FileReader(currentLevel));
			JsonObject  jobject = jelement.getAsJsonObject();
			String levelName = jobject.get("name").getAsString();
			int id = jobject.get("id").getAsInt();
			JsonArray gridCells = jobject.getAsJsonArray("ScrollingGrid");
			LevelSerializer ls = new LevelSerializer();
			ScrollingGrid scrollingGrid = ls.deserialize(gridCells);
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
			
			level.setName(levelName);
			level.setID(id);
			level.setObjects(gameObjects);
			level.updateGrid(scrollingGrid);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return level;
	}


	@Override
	/**
	 * Returns a map of settings items to values. These items include 
	 * a description of the game and a ready to play value.
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
			String description = jobject.get("description").getAsString();
			settingsDetails.put("description", description);
			String ready = jobject.get("readyToPlay").getAsString();
			settingsDetails.put("readyToPlay", ready);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return settingsDetails;
	}
	
	/**
	 * Returns a the File containing the settings information for a game.
	 * 
	 * @param gameName
	 * @return
	 */
	private File retrieveSettings(String gameName) {
		retrieveCurrentGame(gameName);
		return new File(gameDirectory + NEST + SETTINGS + JSON_EXTENSION);
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
	/**
	 * Returns a Map of the names of the ready to play games and their
	 * descriptions.
	 * 
	 * @return
	 */
	public Map<String,String> getGameNames() {
		Map<String,String> gameNames = new HashMap<>();
		File gamesDirectory = new File(GAME_FOLDER);
		File[] games= gamesDirectory.listFiles();
		for(File game: games)
		{
			int index = game.toString().lastIndexOf("\\") + 1;
			String gameName = game.toString().substring(index).trim();
			Map<String,String> gameSettings = loadSettings(gameName);
			if(gameSettings.get("readyToPlay").equals("true"))
			{
				gameNames.put(gameName, gameSettings.get("description"));
			}
		}
		return gameNames;
	}
}


