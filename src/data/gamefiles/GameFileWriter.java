package data.gamefiles;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
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
	private static final String GAMEDATA = "./data/gameData/";
	private static final String LEVELDATA = "./data/levelData/";
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
		gameDirectory = GAMEDATA + gameName;
		gameDirectoryFile = retrieveGame();
	}

	/**
	 * Iterates through all levels and items in levels passed and updates gameToEdit file to reflect adds, removals, changes
	 * @param changes	Map of Levels linked to all the items in them
	 */
	@Override
	public void update(List<AuthoredLevel> changes)	{
		for (AuthoredLevel aLevel:changes)	{
			saveData(aLevel);
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
	 */
	public void saveData(AuthoredLevel level)	{
		new TextWriter(level, getLevel(level.getLevel()));
	}

	/**
	 * Saves state of level being played, for use with checkpoints
	 * @param player			name of level to save
	 */
	@Override
	public void saveData(String player, List<Level> levels)	{
		for (Level aLevel:levels)	{
			new TextWriter(new AuthoredLevel(aLevel, new ScrollingGrid()), getLevel(aLevel, player));
		}
	}

	/**
	 * Cancels any edits made to a game since last save
	 * @param level	level to cancel changes to
	 * @return	new, replacement instance of level
	 */
	@Override
	public Level revertChanges(AuthoredLevel level)	{
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

	/**
	 * Allows for saving individual level as a "stray", not part of a game
	 * @author Maya Messinger
	 * @param level		level to save separately
	 */
	public void saveIndivLevel(AuthoredLevel level)	{
		String tempGameDir = gameDirectory;

		gameDirectory = LEVELDATA;

		saveData(level);

		gameDirectory = tempGameDir;
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
		return (getLevel(level, ""));
	}

	private File getLevel(Level level, String user)	{
		File newLevel = new File(gameDirectory + NEST + level.getName() + user + EXTENSION);

		if(!newLevel.exists())	{
			try {
				newLevel.createNewFile();
			} 
			catch (IOException e) {
				JOptionPane.showMessageDialog(new JFrame(),
				    "Could not get or make file " + newLevel.toString(),
				    "IOException",
				    JOptionPane.WARNING_MESSAGE);
			}
		}

		return newLevel;
	}
}