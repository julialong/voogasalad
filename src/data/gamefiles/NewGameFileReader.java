package data.gamefiles;

import java.io.File;
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
//		currentLevel = new File(gameDirectory + "/" + "Default.json");
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
	
	@Override
	public List<Level> loadCompleteGame(String gameName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Level loadLevel(String gameName, String levelName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> loadSettings(String gameName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getGameNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthoredLevel> loadCompleteAuthoredGame(String gameName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthoredLevel loadAuthoredLevel(String levelName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, GameEntity> loadAuthorCustomObjects(String author) {
		// TODO Auto-generated method stub
		return null;
	}

}
