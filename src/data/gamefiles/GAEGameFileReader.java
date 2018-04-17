package data.gamefiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoring_environment.game_elements.AuthoredLevel;
import engine.entity.GameEntity;
import engine.level.Level;

public class GAEGameFileReader implements JSONtoGAE {
	
	private static final String JSON_EXTENSION = ".json";
	private static final String SETTINGS = "Settings";
	private String NEST = "/";
	private FileRetriever fileRetriever;
	
	public GAEGameFileReader() 
	{
		if (System.getProperty("os.name").toString().contains("Windows"))	
		{
			NEST = "\\";
		}
		fileRetriever = new FileRetriever();
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
		List<AuthoredLevel> completeGame = new ArrayList<>();
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
