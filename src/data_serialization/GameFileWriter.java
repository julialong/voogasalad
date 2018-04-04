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
public class GameFileWriter implements GAEtoJSON, GEtoJSON {

	public String gameDirectory;
	public GameFile gameToEdit;
	
	/**
	 * Class Constructor.
	 * Creates or loads the appropriate GameFile object for a game. 
	 * 
	 * @param gameName
	 */
	public GameFileWriter(String gameName)	{
		gameDirectory = "./data/gameData/" + gameName;
		gameToEdit = loadGame();
	}

	public void update(Map<Level, List<List<GameObject>>> changes)	{
		for (Level aLevel:changes.keySet())	{
			List<List<GameObject>> filesToEdit = changes.get(aLevel);

			for (GameObject added:filesToEdit.get(0))	{
				// add new item to database
			}
			for (GameObject changed:filesToEdit.get(1))	{
				// find item in database by id, update it with new properties
			}
			for (GameObject removed:filesToEdit.get(2))	{
				// find item in database by id, remove it from database
			}
		}
	}

	public void saveData(Level level, List itemsInLevel)	{
	}

	public List<Object> revertChanges(String gameName)	{
		return null;
	}
	
	private GameFile retrieveGame()	{
		File gameFolder = new File(gameDirectory);
		if (!gameExists(gameFolder))
		{
			gameFolder.mkdir();
		}
		return new GameFile(gameDirectory);
	}

	private boolean gameExists(File gameFolder)	{
		if (!gameFolder.exists() && !gameFolder.isDirectory())	{
			return false;
		}
		return true;
	}
}
