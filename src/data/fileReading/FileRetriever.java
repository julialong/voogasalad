package data.fileReading;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileRetriever {

//	private static final String GAME_FOLDER = "./data/gameData";
	private static final String GAME_FOLDER = "./data/testingGameData";
	private static final String JSON_EXTENSION = ".json";
	private static final String SETTINGS = "Settings";
	private String NEST = "/";
	
	public FileRetriever()
	{
		if (System.getProperty("os.name").contains("Windows"))	
		{
			NEST = "\\";
		}
	}
	
	public List<String> retrieveAllGamePaths()
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
		//error for no games
		return gamePaths;
	}
	/**
	 * Returns the file associated to the game name
	 * 
	 * @param gameName
	 */
	public String retrieveCurrentGamePath(String gameName)
	{
		List<String> allGamePaths = retrieveAllGamePaths();
		for(String gamePath: allGamePaths)
		{
			if(gamePath.contains(gameName))
			{
				return gamePath;
			}	
		}
		//ERROR FOR CAN'T FIND THE FILE
		return null;
	}
	
	/**
	 * Return the file for the specific level
	 * 
	 * @param gameName
	 * @param level
	 */
	public File retrieveLevel(String gameName, String level)
	{
		String gameDirectory = retrieveCurrentGamePath(gameName);
		return new File(gameDirectory + NEST + level + JSON_EXTENSION);
	}
	
	/**
	 * Returns a the File containing the settings information for a game.
	 * 
	 * @param gameName
	 * @return
	 */
	public File retrieveSettings(String gameName) {
		String gameDirectory = retrieveCurrentGamePath(gameName);
		return new File(gameDirectory + NEST + SETTINGS + JSON_EXTENSION);
	}
}
