package data.gamefiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.serialization.GameObject;
import data.serialization.Level;
import data.serialization.TextWriter;

/**
 * @author Maya messinger
 * @author Belanie Nagiel
 * Started: 3 Apr 18
 * Class that facilitates addition and removal of items in the levels of a game.
 * Creates appropriate folders and files based on whether the game exists or 
 * not and connects to the GameFile object.
 */
public class GameFileWriter implements GAEtoJSON, GEtoJSON	{
	private String gameDirectory;
	private File gameDirectoryFile;
	private List<Object> objsToWrite = new ArrayList<>();

	/**
	 * Class Constructor.
	 * Creates or loads the appropriate GameFile object for a game.
	 * @param gameName
	 */
	public GameFileWriter(String gameName)	{
		gameDirectory = "./data/gameData/" + gameName;
		gameDirectoryFile = retrieveGame();
	}

	/**
	 * Iterates through all levels and items in levels passed and updates gameToEdit file to reflect adds, removals, changes
	 * @param changes	Map of Levels linked to all the items in them
	 */
	@Override
	public void update(Map<Level, List<GameObject>> changes)	{
		for (Level aLevel:changes.keySet())	{
			saveData(aLevel, changes.get(aLevel));
		}
	}

	/**
	 * Saves state of level being played, for use with checkpoints
	 * @param level			name of level to save
	 * @param itemsInLevel	List (potentially list of lists of different types of objects) if items in level to save stats of
	 */
	@Override
	public void saveData(Level level, List itemsInLevel)	{
		new TextWriter(getLevel(level), itemsInLevel);
	}

	/**
	 * Cancels any edits made to a game since last save
	 * @param level	name of level to chancel changes to
	 * @return	List of objects in level from JSON file of level
	 */
	@Override
	public List<Object> revertChanges(Level level)	{
		// jsonToObject(getLevel(level));
		return null;
	}
	
	private File retrieveGame()	{
		File gameFolder = new File(gameDirectory);
		if (!gameExists(gameFolder))
		{
			gameFolder.mkdir();
		}
		return gameFolder;
	}

	private boolean gameExists(File gameFolder)	{
		if (!gameFolder.exists() && !gameFolder.isDirectory())	{
			return false;
		}
		return true;
	}

	private File getLevel(Level level)	{
		File newLevel = new File(gameDirectory + level.toString() + ".json");

		if(!newLevel.exists())	{
			try {
				newLevel.createNewFile();
			} 
			catch (IOException e) {
				// TODO proper error
				e.printStackTrace();
			}
		}

		return newLevel;
	}
}