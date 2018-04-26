package data.fileReading;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring_environment.game_elements.AuthoredLevel;
import data.builders.AuthoredLevelBuilder;
import data.resources.DataFileException;
import engine.entity.GameEntity;
/**
 * This class holds the implementation for the methods that allow the GAE to load games and files
 * for continued editing or use.
 * 
 * @author Belanie Nagiel
 *
 */
public class GAEGameFileReader extends GameFileReader implements JSONtoGAE {
	
	/**
	 * Class Constructor
	 */
	public GAEGameFileReader() 
	{
	}
	
	/**
	 * This method will return the list of AuthoredLevel objects created from the level
	 * files for a particular game. This will locate the folder for the specific
	 * game and load the different level files associated with that folder. This will 
	 * allow the game authoring environment to do continued editing on a game.
	 * 
	 * @param gameName
	 * @return 
	 * @throws DataFileException 
	 */
	@Override
	public List<AuthoredLevel> loadCompleteAuthoredGame(String gameName) throws DataFileException {
		List<AuthoredLevel> completeGame = new ArrayList<>();
		File currentGame = new File(getCurrentGamePath(gameName));
		System.out.println("current game " + currentGame.toString());
		File[] gameFiles = currentGame.listFiles();
		System.out.println("current game files " + gameFiles);
		for(File gameFile: gameFiles)
		{
				System.out.println("gameFile " + gameFile.toString());
				int index = gameFile.toString().lastIndexOf(NEST) + 1;
				int endIndex = gameFile.toString().lastIndexOf(JSON_EXTENSION);
				String levelName = gameFile.toString().substring(index,endIndex).trim();
				if(!levelName.equals(SETTINGS))
				{
					System.out.println("level name " +levelName);
					completeGame.add(loadAuthoredGameLevel(gameName, levelName));
				}		
		}
		return completeGame;
	}
	
	/**
	 * This will load an existing level in an existing game as an AuthoredLevel
	 * for use by the game authoring environment. Also used by GameFileWriter for 
	 * reverting changes.
	 * 
	 * @param gameName
	 * @param levelName
	 * @return
	 * @throws DataFileException 
	 */
	@Override
	public AuthoredLevel loadAuthoredGameLevel(String gameName, String levelName) throws DataFileException {
		File level = getLevel(gameName, levelName);
		System.out.println("authored level " + level);
		AuthoredLevelBuilder authoredBuilder = new AuthoredLevelBuilder(level);
		return authoredBuilder.buildAuthoredLevel();
	}

	/**
	 * This will load one of the stray levels that an author can
	 * add to his or her game in the game authoring environment.
	 * 
	 * @param levelName
	 * @return
	 * @throws DataFileException 
	 */
	@Override
	public AuthoredLevel loadAuthoredLevel(String levelName) throws DataFileException {
		File level = new File(LEVEL_FOLDER + NEST + levelName);
		AuthoredLevelBuilder levelBuilder = new AuthoredLevelBuilder(level);
		return levelBuilder.buildAuthoredLevel();
	}
	
	/**
	 * This will load the names of all of the stray levels so that a user in the authoring
	 * environment can load a pre-existing level.
	 * 
	 * @return
	 */
	@Override
	public List<String> loadAuthoredLevelNames() {
		List<String> levelNames = new ArrayList<>();
		File strayLevelFolder = new File(LEVEL_FOLDER);
		File[] strayLevels = strayLevelFolder.listFiles();
		for(File strayLevel: strayLevels)
		{
			int index = strayLevel.toString().lastIndexOf(NEST) + 1;
			String levelName = strayLevel.toString().substring(index).trim();
			levelNames.add(levelName);
		}
		return levelNames;
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

	/**
	 * This returns a Map of the names of the games for continued editing.
	 * 
	 * @return
	 * @throws DataFileException 
	 */
	@Override
	public Map<String, String> getGameNames() throws DataFileException {
		Map<String,String> gameNames = new HashMap<>();
		List<String> allGameNames = getAllGameNames();
		for(String gameName: allGameNames)
		{
			Map<String,String> gameSettings = getSettingsMap(gameName);
			gameNames.put(gameName, gameSettings.get(DESCRIPTION));
		}
		return gameNames;
	}


}
