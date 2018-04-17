package data.fileReading;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import engine.level.Level;

public class GPGameFileReader implements JSONtoGP{

	private static final String JSON_EXTENSION = ".json";
	private static final String SETTINGS = "Settings";
	private String NEST = "/";
	private FileRetriever fileRetriever;
	
	public GPGameFileReader()
	{
		if (System.getProperty("os.name").toString().contains("Windows"))	
		{
			NEST = "\\";
		}
		fileRetriever = new FileRetriever();
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
		File currentGame = new File(fileRetriever.retrieveCurrentGamePath(gameName));
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
		File currentLevel = fileRetriever.retrieveLevel(gameName, levelName);
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
		
		//Sort through authors
		return null;
	}

}
