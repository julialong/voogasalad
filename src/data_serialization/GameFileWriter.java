package data_serialization;

import java.io.File;
/**
 * @author Belanie Nagiel
 * 
 * Class that facilitates addition and removal of items in the levels of a game.
 * Creates appropriate folders and files based on whether the game exists or 
 * not and connects to the GameFile object.
 *
 */
public class GameFileWriter {

	public String gameDirectory;
	public GameFile gameToEdit;
	
	/**
	 * Class Constructor.
	 * Creates or loads the appropriate GameFile object for a game. 
	 * 
	 * @param gameName
	 */
	public GameFileWriter(String gameName) 
	{
		gameDirectory = "./data/gameData/" + gameName;
		loadGame();
	}
	
	/**
	 * Creates the GameFile object for the given Game
	 */
	private void loadGame()
	{
		File gameFolder = new File(gameDirectory);
		if (!gameFolder.exists() && !gameFolder.isDirectory())
		{
			gameFolder.mkdir();
		}
		gameToEdit = new GameFile(gameDirectory);
	}
	
	/**
	 * Adds the string info to the json file for a particular level.
	 * 
	 * @param level Level to be edited
	 * @param updateInfo JSON information to be added
	 */
	public void add(int level, String updateInfo)
	{
		gameToEdit.writeToLevel(level, updateInfo);
	}
	
	public void remove(int level, String updateInfo)
	{
	}
	
}
