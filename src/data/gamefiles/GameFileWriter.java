package data.gamefiles;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import data.serialization.TextWriter;
import engine.entity.GameEntity;
import engine.level.Level;

/**
 * @author Maya Messinger
 * @author Belanie Nagiel
 * Started: 3 Apr 18
 * Class that facilitates addition and removal of items in the levels of a game.
 * Creates appropriate folders and files based on whether the game exists or 
 * not and connects to the GameFile object.
 */
public class GameFileWriter implements GAEtoJSON, GEtoJSON	{
	private static final String NEST = "/";
	private static final String SETTINGS = "Settings";
	private static final String EXTENSION = ".json";

	private String gameDirectory;
	private File gameDirectoryFile;
	private String gameName;

	/**
	 * Class Constructor.
	 * Creates or loads the appropriate GameFile object for a game.
	 * @param gameName
	 */
	public GameFileWriter(String gameName)	{
		this.gameName = gameName;
		gameDirectory = "./data/gameData/" + gameName;
		gameDirectoryFile = retrieveGame();
	}

	/**
	 * Iterates through all levels and items in levels passed and updates gameToEdit file to reflect adds, removals, changes
	 * @param changes	Map of Levels linked to all the items in them
	 */
	@Override
	public void update(List<Level> changes)	{
		for (Level aLevel:changes)	{
			saveData(aLevel, aLevel.getObjects());
		}
	}

	/**
	 * For testing only
	 */
	public void update(Map<Level, List<GameEntity>> changes)	{
		for (Level aLevel:changes.keySet())	{
			saveData(aLevel, changes.get(aLevel));
		}
	}

	/**
	 * Will change settings file mapping to whether game is ready to play or not
	 * @param ready		value to change readiness of game to
	 */
	@Override
	public void updateMeta(boolean ready, String desc)	{
		new TextWriter(new File(gameDirectory + NEST + SETTINGS + EXTENSION), ready, desc);
	}

	/**
	 * Saves state of level being played, for use with checkpoints
	 * @param level			name of level to save
	 * @param itemsInLevel	List (potentially list of lists of different types of objects) if items in level to save stats of
	 */
	@Override
	public void saveData(Level level, List itemsInLevel)	{
		new TextWriter(level, getLevel(level), itemsInLevel);
	}

	/**
	 * Cancels any edits made to a game since last save
	 * @param level	name of level to cancel changes to
	 * @return	List of objects in level from JSON file of level
	 */
	@Override
	public Level revertChanges(Level level)	{
		GameFileReader reader = new GameFileReader();
		String levelName = level.getName();
		return reader.loadLevel(gameName, levelName);
	}

	/**
	 * Method to rename a game (folder)
	 * @param newName	String to rename game to	
	 */
	@Override
	public void renameGame(String newName)	{
		File newDir = new File(gameDirectoryFile.getParent() + NEST + newName);

		gameDirectoryFile.renameTo(newDir);
	}
	
	private File retrieveGame()	{
		File gameFolder = new File(gameDirectory);
		if (!gameExists(gameFolder))
		{
			makeNewGame(gameFolder);
		}
		return gameFolder;
	}

	private boolean gameExists(File gameFolder)	{
		if (!gameFolder.exists() && !gameFolder.isDirectory())	{
			return false;
		}
		return true;
	}

	private void makeNewGame(File gameFolder)	{
		gameFolder.mkdir();

		new TextWriter(new File(gameDirectory + NEST + SETTINGS + EXTENSION), false, "no description");
	}

	private File getLevel(Level level)	{
		File newLevel = new File(gameDirectory + NEST + level.getName() + EXTENSION);

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