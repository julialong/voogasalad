package data.gamefiles;

import java.io.File;
import java.io.IOException;
import java.util.List;

import data.fileReading.GAEGameFileReader;
import data.resources.DataFileException;
import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
import data.fileReading.GAEGameFileReader;
import data.resources.DataFileException;
import data.serialization.TextWriter;
import engine.level.Level;

import javax.xml.crypto.Data;

/**
 * @author Maya Messinger
 * @author Belanie Nagiel
 * Started: 3 Apr 18
 * Class that facilitates addition and removal of items in the levels of a game.
 * Creates appropriate folders and files based on whether the game exists or 
 * not and connects to the GameFile object.
 */
public class GameFileWriter implements GAEtoJSON, GEtoJSON	{
	private static final String GAMEDATA = "./data/gameData/";
	private static final String LEVELDATA = "./data/levelData/";
	private static final String NEST = File.separator;
	private static final String SETTINGS = "Settings";
	private static final String EXTENSION = ".json";

	private String userDirectory;
	private File userDirectoryFile;
	private String gameDirectory;
	private File gameDirectoryFile;
	private String gameName;

	/**
	 * Class Constructor.
	 * Creates or loads the appropriate GameFile object for a game.
	 * @param gameName
	 */
	public GameFileWriter(String user, String gameName) throws DataFileException	{
		this.gameName = gameName;
		userDirectory = GAMEDATA + user;
		userDirectoryFile = retrieveFolder(userDirectory);
		gameDirectory = userDirectory + NEST + gameName;
		gameDirectoryFile = retrieveFolder(gameDirectory);
	}

	/**
	 * Iterates through all levels and items in levels passed and updates gameToEdit file to reflect adds, removals, changes
	 * @param changes	Map of Levels linked to all the items in them
	 */
	@Override
	public void update(List<AuthoredLevel> changes) throws DataFileException	{
		for (AuthoredLevel aLevel:changes)	{
			saveData(aLevel);
		}
	}

	/**
	 * Will change settings file mapping to whether game is ready to play or not
	 * @param ready		value to change readiness of game to
	 * @param desc		description of game
	 */
	@Override
	public void updateMeta(boolean ready, String desc) throws DataFileException	{
		updateMeta(ready, desc, 0);
	}

	/**
	 * Will change settings file mapping to whether game is ready to play or not
	 * @param ready			value to change readiness of game to
	 * @param desc			description of game
	 * @param levelStart	level to start game at
	 */
	@Override
	public void updateMeta(boolean ready, String desc, int levelStart) throws DataFileException	{
		new TextWriter(new File(gameDirectory + NEST + SETTINGS + EXTENSION), ready, desc, levelStart);
	}

	/**
	 * Saves state of level being played, for use with checkpoints
	 * @param level			name of level to save
	 */
	@Override
	@Deprecated
	public void saveData(Level level) throws DataFileException	{
		new TextWriter(new AuthoredLevel(level, new ScrollingGrid()), getLevel(level));
	}

	/**
	 * Saves state of level being played, for use with checkpoints
	 * @param level			name of level to save
	 */
	public void saveData(AuthoredLevel level) throws DataFileException	{
		new TextWriter(level, getLevel(level.getLevel()));
	}

	/**
	 * Saves state of level being played, for use with checkpoints
	 * @param player			name of level to save
	 */
	@Override
	public void saveData(String player, List<Level> levels) throws DataFileException	{
		for (Level aLevel:levels)	{
			new TextWriter(new AuthoredLevel(aLevel, new ScrollingGrid()), getLevel(aLevel, player));
		}
	}

	/**
	 * Cancels any edits made to a game since last save
	 * @param level	level to cancel changes to
	 * @return	new, replacement instance of level
	 * @throws DataFileException 
	 */
	@Override
	@Deprecated
	public Level revertChanges(Level level) throws DataFileException	{
		GAEGameFileReader reader = new GAEGameFileReader();
		String levelName = level.getName();
		return reader.loadAuthoredGameLevel(gameName, levelName).getLevel();
	}

	/**
	 * Cancels any edits made to a game since last save
	 * @param level	level to cancel changes to
	 * @return new, replacement instance of level
	 * @throws DataFileException 
	 */
	@Override
	public AuthoredLevel revertChanges(AuthoredLevel level) throws DataFileException	{
		GAEGameFileReader reader = new GAEGameFileReader();
		String levelName = level.getName();
		return reader.loadAuthoredGameLevel(gameName, levelName);
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

	/**
	 * Allows for saving individual level as a "stray", not part of a game
	 * @author Maya Messinger
	 * @param level		level to save separately
	 */
	public void saveIndivLevel(AuthoredLevel level) throws DataFileException	{
		String tempGameDir = gameDirectory;

		gameDirectory = LEVELDATA;

		saveData(level);

		gameDirectory = tempGameDir;
	}
	
	private File retrieveFolder(String lookFor) throws DataFileException	{
		File folder = new File(lookFor);
		if (!gameExists(folder))	{
			makeNewGame(folder);
		}
		return folder;
	}

	private boolean gameExists(File gameFolder)	{
		 return gameFolder.exists() && gameFolder.isDirectory();
	}

	private void makeNewGame(File gameFolder) throws DataFileException	{
		gameFolder.mkdir();

		if (gameDirectory != null)	{
			new TextWriter(new File(gameDirectory + NEST + SETTINGS + EXTENSION), true, "no description", 0);
		}
	}

	private File getLevel(Level level) throws DataFileException	{
		return (getLevel(level, ""));
	}

	private File getLevel(Level level, String user) throws DataFileException {
		File newLevel = new File(gameDirectory + NEST + level.getName() + user + EXTENSION);

		if(!newLevel.exists())	{
			try {
				newLevel.createNewFile();
			} 
			catch (IOException e) {
				throw new DataFileException("Could not get or make file " + newLevel.toString(), new Throwable("IOException in GameFileWriter"));
			}
		}

		return newLevel;
	}
}