package data.fileReading;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import data.resources.DataFileException;

public abstract class GameFileReader {
	
	protected static final String GAME_FOLDER = "./data/gameData";
	protected static final String JSON_EXTENSION = ".json";
	protected static final String SETTINGS = "Settings";
	protected static final String[] SETTINGS_DATA = {"description", "readyToPlay"};
	protected static final String NEST = File.separator;
	protected static final String PLAYER_FOLDER = "Playing";
	protected static final String PLAYER_SEPARATOR = "_";
	protected static final String LEVEL_FOLDER = "./data/levelData";
	
	/**
	 * Returns a list of all the possible game paths
	 * 
	 * @return
	 */
	protected List<String> getAllGamePaths()
	{
		List<String> gamePaths = new ArrayList<>();
		File gameFolder = new File(GAME_FOLDER);
		File[] authors = gameFolder.listFiles();
		for(File author: authors)
		{
			File[] games = author.listFiles();
			for(File game: games)
			{
				gamePaths.add(game.toString());
			}
		}	
		// TODO error for no games
		return gamePaths;
	}
	
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
			String regexStart = "^";
			String regexAny = ".*";
			String regexEnd = "$";
			if(gamePath.matches(regexStart + regexAny + gameName + regexEnd))
			{
				return gamePath;
			}	
		}
		// TODO ERROR FOR CAN'T FIND THE FILE
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
	
	protected Map<String,String> getSettingsMap(String gameName) throws DataFileException
	{
		Map<String,String> settingsDetails = new HashMap<>();
		File settings = getSettings(gameName);
		try {
			JsonParser jsonParser = new JsonParser();
			JsonElement jelement;
			jelement = jsonParser.parse(new FileReader(settings));
			JsonObject  jobject = jelement.getAsJsonObject();
			for(String metadata: SETTINGS_DATA)
			{
				String info = jobject.get(metadata).getAsString();
				settingsDetails.put(metadata, info);
			}
			return settingsDetails;
		}
		catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) 
		{
			throw new DataFileException("Could not find settings file for" + gameName, e);
		}
	}
	

}
