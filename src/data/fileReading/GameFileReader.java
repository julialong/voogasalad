package data.fileReading;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import data.resources.DataFileException;
/**
 * Implements methods for getting file paths and getting settings that are used in the GameFileReaders for both Game Authoring
 * Environment and Game Player. Also holds the static final variables that will be used in the two file readers.
 * 
 * @author Belanie Nagiel
 *
 */
public abstract class GameFileReader {
	
	protected static final String GAME_FOLDER = "./data/gameData";
	protected static final String JSON_EXTENSION = ".json";
	protected static final String SETTINGS = "Settings";
	protected static final String DESCRIPTION = "description";
	protected static final String READY = "readyToPlay";
	protected static final String[] SETTINGS_DATA = {DESCRIPTION, READY};
	protected static final String NEST = File.separator;
	protected static final String PLAYER_FOLDER = "Playing";
	protected static final String PLAYER_SEPARATOR = "_";
	protected static final String LEVEL_FOLDER = "./data/levelData";
	protected static final String LEVEL_ORDER = "LevelOrder";
	protected static final String ORDER = "order";
	
	/**
	 * Returns a list of all the possible game paths
	 * 
	 * @return
	 */
	protected List<String> getAllGamePaths()
	{
		List<String> gamePaths = new ArrayList<>();
		File gameFolder = new File(GAME_FOLDER);
		System.out.println("foldr " + gameFolder.toString());
		File[] authors = gameFolder.listFiles();
		for(File author: authors)
		{
			System.out.println("author" + author.toString());
			File[] games = author.listFiles();
			System.out.println("game " + games.toString());
			for(File game: games)
			{
				gamePaths.add(game.toString());
			}
		}	
		return gamePaths;
	}
	
	/**
	 * Returns a list of all of the game names
	 * 
	 * @return
	 */
	protected List<String> getAllGameNames()
	{
		List<String> allGamePaths = getAllGamePaths();
		List<String> allGameNames = new ArrayList<>();
		for(String gamePath: allGamePaths)
		{
			int index = gamePath.lastIndexOf(NEST) + 1;
			allGameNames.add(gamePath.substring(index).trim());
		}
		return allGameNames;	
	}
	
	/**
	 * Returns the String game path for the game name given 
	 * as a parameter
	 * 
	 * @param gameName
	 */
	protected String getCurrentGamePath(String gameName)
	{
		List<String> allGamePaths = getAllGamePaths();
		for(String gamePath: allGamePaths)
		{
			int index = gamePath.lastIndexOf(NEST) + 1;
			String game = gamePath.substring(index);
			if(game.equals(gameName))
			{
				return gamePath;
			}	
		}
		return null;
	}
	
	/**
	 * Return the file for the specific level
	 * 
	 * @param gameName
	 * @param level
	 */
	protected File getLevel(String gameName, String level)
	{
		String gameDirectory = getCurrentGamePath(gameName);
		return new File(gameDirectory + NEST + level + JSON_EXTENSION);
	}
	
	/**
	 * Return the file for the specific player's version of a level
	 * 
	 * @param gameName
	 * @param level
	 * @param player
	 * @return
	 */
	protected File getPlayerLevel(String gameName, String level, String player)
	{
		String gameDirectory = getCurrentGamePath(gameName);
		return new File(gameDirectory + NEST + PLAYER_FOLDER + NEST + level + PLAYER_SEPARATOR + player + JSON_EXTENSION);
	}
	
	/**
	 * Returns a the File containing the settings information for a game.
	 * 
	 * @param gameName
	 * @return
	 */
	protected File getSettings(String gameName) {
		String gameDirectory = getCurrentGamePath(gameName);
		return new File(gameDirectory + NEST + SETTINGS + JSON_EXTENSION);
	}
	
	/**
	 * Returns map of keys to values for the settings file of the given game.
	 * 
	 * @param gameName
	 * @return
	 * @throws DataFileException
	 */
	protected Map<String,String> getSettingsMap(String gameName) throws DataFileException
	{
		Map<String,String> settingsDetails = new HashMap<>();
		File settings = getSettings(gameName);
		try {
			return createSettingsMap(settings, settingsDetails);
		}
		catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) 
		{
			throw new DataFileException("Could not find settings file for" + gameName, e);
		}
	}
	
	/**
	 * Creates the map of keys to values from items in the json file for settings.
	 * 
	 * @param settings
	 * @param settingsDetails
	 * @return
	 * @throws JsonIOException
	 * @throws JsonSyntaxException
	 * @throws FileNotFoundException
	 */
	private Map<String, String> createSettingsMap(File settings, Map<String, String> settingsDetails) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		JsonParser jsonParser = new JsonParser();
		JsonObject  jobject = jsonParser.parse(new FileReader(settings)).getAsJsonObject();
		for(String metadata: SETTINGS_DATA)
		{
			String info = jobject.get(metadata).getAsString();
			settingsDetails.put(metadata, info);
		}
		return settingsDetails;
	}

	/**
	 * Returns the map of level numbers to names for later use in ordering levels.
	 * 
	 * @param gameName
	 * @return
	 * @throws DataFileException
	 */
	public Map<String,Integer> getLevelOrder(String gameName) throws DataFileException
	{
		Map<String,Integer> levelOrder = new HashMap<>();
		String gameDirectory = getCurrentGamePath(gameName);
		File levelOrderFile = new File(gameDirectory + NEST + LEVEL_ORDER + JSON_EXTENSION);
		try 
		{
			return makeLevelOrder(levelOrderFile, levelOrder);
		} 
		catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) 
		{
			throw new DataFileException("Could not find level order file for" + gameName, e);
		}
	}

	/**
	 *  Creates the map of level numbers to names from the LevelOrder json file for the game
	 * 
	 * @param levelOrderFile
	 * @param levelOrder
	 * @return
	 * @throws JsonIOException
	 * @throws JsonSyntaxException
	 * @throws FileNotFoundException
	 */
	private Map<String, Integer> makeLevelOrder(File levelOrderFile, Map<String, Integer> levelOrder) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		JsonParser jsonParser = new JsonParser();
		JsonObject  jobject = jsonParser.parse(new FileReader(levelOrderFile)).getAsJsonObject();
		JsonArray jarray = jobject.get(ORDER).getAsJsonArray();
		for(int i = 0; i < jarray.size(); i++)
		{
			levelOrder.put(jarray.get(i).getAsString(), i);
		}
		return levelOrder;
	}

}
