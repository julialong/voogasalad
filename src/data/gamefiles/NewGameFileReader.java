package data.gamefiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoring_environment.game_elements.AuthoredLevel;
import engine.entity.GameEntity;
import engine.level.Level;

public class NewGameFileReader implements JSONtoGAE, JSONtoGP{

	private static final String GAME_FOLDER = "./data/gameData";
	private static final String JSON_EXTENSION = ".json";
	private static final String SETTINGS = "Settings";
	private String NEST = "/";
	
	public NewGameFileReader()
	{
		if (System.getProperty("os.name").toString().contains("Windows"))	
		{
			NEST = "\\";
		}
	}
	
	/**
	 * Returns the file associated to the game name
	 * 
	 * @param gameName
	 */
	private File retrieveCurrentGame(String gameName)
	{
		return new File(GAME_FOLDER + NEST + gameName);
	}
	
	/**
	 * Return the file for the specific level
	 * 
	 * @param gameName
	 * @param level
	 */
	private File retrieveLevel(String gameName, String level)
	{
		String gameDirectory = GAME_FOLDER + NEST + gameName;
		return new File(gameDirectory + NEST + level + JSON_EXTENSION);
	}
	
	/**
	 * Returns a the File containing the settings information for a game.
	 * 
	 * @param gameName
	 * @return
	 */
	private File retrieveSettings(String gameName) {
		String gameDirectory = GAME_FOLDER + NEST + gameName;
		return new File(gameDirectory + NEST + SETTINGS + JSON_EXTENSION);
	}
	
	/**
	 * This method will return the list of Level objects created from the level
	 * files for a particular game. This will locate the folder for the specific
	 * game and load the different level files associated with that folder.
	 * 
	 * @param gameName
	 * @return 
	 */
	@Override
	public List<Level> loadCompleteGame(String gameName) {
		List<Level> completeGame = new ArrayList<>();
		File currentGame = retrieveCurrentGame(gameName);
		File[] gameFiles = currentGame.listFiles();
		for(File gameFile: gameFiles)
		{
				int index = gameFile.toString().lastIndexOf(NEST) + 1;
				int endIndex = gameFile.toString().lastIndexOf(JSON_EXTENSION);
				String levelName = gameFile.toString().substring(index,endIndex).trim();
				if(!levelName.equals(SETTINGS))
				{
					completeGame.add(loadLevel(gameName, levelName));
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
	 */
	@Override
	public Level loadLevel(String gameName, String levelName) {
		File currentLevel = retrieveLevel(gameName, levelName);
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
	 */
	@Override
	public Map<String, String> loadSettings(String gameName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This returns a Map of the names of the ready to play games and their
	 * descriptions.
	 * 
	 * @return
	 */
	@Override
	public Map<String, String> getGameNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This method will return the list of AuthoredLevel objects created from the level
	 * files for a particular game. This will locate the folder for the specific
	 * game and load the different level files associated with that folder. This will 
	 * allow the game authoring environment to do continued editing on a game.
	 * 
	 * @param gameName
	 * @return 
	 */
	@Override
	public List<AuthoredLevel> loadCompleteAuthoredGame(String gameName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This will load one of the stray levels that an author can
	 * add to his or her game in the game authoring environment.
	 * 
	 * @param levelName
	 * @return
	 */
	@Override
	public AuthoredLevel loadAuthoredLevel(String levelName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This will load the author settings for a specific author in the 
	 * game authoring environment. It will return the map of image paths 
	 * to custom game entities. 
	 * 
	 * @param author
	 * @return
	 */
	@Override
	public Map<String, GameEntity> loadAuthorCustomObjects(String author) {
		// TODO Auto-generated method stub
		return null;
	}

}
