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

import data.builders.LevelBuilder;
import data.resources.DataFileException;
import engine.level.Level;
/**
 *  This class holds the implementation for the methods that allow the Game Player to load games and files
 * for play.
 * 
 * @author Belanie Nagiel
 *
 */
public class GPGameFileReader extends GameFileReader implements JSONtoGP{
	/**
	 * Class Constructor
	 */
	public GPGameFileReader()
	{

	}
	
	/**
	 * This method will return the list of Level objects created from the level
	 * files for a particular game. This will locate the folder for the specific
	 * game and load the different level files associated with that folder.
	 * 
	 * @param gameName
	 * @return 
	 * @throws DataFileException 
	 */
	@Override
	public List<Level> loadCompleteGame(String gameName) throws DataFileException {
		List<Level> completeGame = new ArrayList<>();
		File currentGame = new File(getCurrentGamePath(gameName));
		File[] gameFiles = currentGame.listFiles();
		for(File gameFile: gameFiles)
		{
			if(!gameFile.isDirectory())
			{
				int index = gameFile.toString().lastIndexOf(NEST) + 1;
				int endIndex = gameFile.toString().lastIndexOf(JSON_EXTENSION);
				String levelName = gameFile.toString().substring(index,endIndex).trim();
				if(!levelName.equals(SETTINGS))
				{
					completeGame.add(loadLevel(gameName, levelName));
				}
			}			
		}
		return completeGame;
	}

	/**
	 * This will load a specific level within a game based on the game name 
	 * and the name of the level. Will return the Level object associated with 
	 * that level file.
	 * 
	 * @param gameName
	 * @param levelName
	 * @return
	 * @throws DataFileException 
	 */
	@Override
	public Level loadLevel(String gameName, String levelName) throws DataFileException {
		File currentLevel = getLevel(gameName, levelName);
		LevelBuilder levelBuilder = new LevelBuilder(currentLevel);
		return levelBuilder.buildLevel();
	}

	/**
	 * This will load the different settings associated with a game, i.e the 
	 * description of the game and whether or not it is ready to be played.
	 * Returns a map of the setting to the value.
	 * 
	 * @param gameName
	 * @return
	 * @throws DataFileException 
	 */
	@Override
	public Map<String, String> loadSettings(String gameName) throws DataFileException {
		return getSettingsMap(gameName);
	}

	/**
	 * This returns a Map of the names of the ready to play games and their
	 * descriptions.
	 *
	 * @return
	 * @throws DataFileException 
	 */
	@Override
	public Map<String, String> getGameNames() throws DataFileException {
		Map<String,String> gameNames = new HashMap<>();
		List<String> allGameNames = getAllGameNames();
		for(String gameName: allGameNames)
		{
			Map<String,String> gameSettings = loadSettings(gameName);
			if(gameSettings.get(READY).equals("true"))
			{
				gameNames.put(gameName, gameSettings.get(DESCRIPTION));
			}
		}
		return gameNames;
	}
}
