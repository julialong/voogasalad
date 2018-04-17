package data.gamefiles;

import java.io.File;

public class FileRetriever {

	private static final String GAME_FOLDER = "./data/gameData";
	private static final String JSON_EXTENSION = ".json";
	private static final String SETTINGS = "Settings";
	private String NEST = "/";
	
	public FileRetriever()
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
	public String retrieveCurrentGamePath(String gameName)
	{
		//Search through authors
		//NEEDS TO BE TESTED
		File gameFolder = new File(GAME_FOLDER);
		File[] authors = gameFolder.listFiles();
		for(File author: authors)
		{
			File[] games = author.listFiles();
			for(File game: games)
			{
				if(game.toString().contains(gameName))
				{
					return game.toString();
				}
			}
		}
		return null;
//		return new File(GAME_FOLDER + NEST + gameName);
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
