package data.fileReading;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring_environment.game_elements.AuthoredLevel;
import data.levelBuilders.AuthoredLevelBuilder;
import data.resources.DataFileException;

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
		//Constructor is empty
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
		File[] gameFiles = currentGame.listFiles();
		for(File gameFile: gameFiles)
		{
			addLevel(gameFile, completeGame, gameName);
		}
		completeGame = orderLevels(gameName,completeGame);
		return completeGame;
	}
	
	private void addLevel(File gameFile, List<AuthoredLevel> completeGame, String gameName) throws DataFileException {
		int index = gameFile.toString().lastIndexOf(NEST) + 1;
		int endIndex = gameFile.toString().lastIndexOf(JSON_EXTENSION);
		String levelName = gameFile.toString().substring(index,endIndex).trim();
		if(!levelName.equals(SETTINGS) && !levelName.equals(LEVEL_ORDER))
		{
			completeGame.add(loadAuthoredGameLevel(gameName, levelName));
		}		
	}

	private List<AuthoredLevel> orderLevels(String gameName, List<AuthoredLevel> levels) throws DataFileException
	{
		Map<String,Integer> levelOrder = getLevelOrder(gameName);
		AuthoredLevel[] orderedLevels = new AuthoredLevel[levels.size()];
		for(AuthoredLevel level: levels)
		{
			int position = levelOrder.get(level.getLevel().getName());
			orderedLevels[position] = level;
		}
		System.out.println(orderedLevels);
		return Arrays.asList(orderedLevels);
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
		File level = new File(LEVEL_FOLDER + NEST + levelName + JSON_EXTENSION);
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
			int endIndex = strayLevel.toString().lastIndexOf(JSON_EXTENSION);
			String levelName = strayLevel.toString().substring(index, endIndex).trim();
			levelNames.add(levelName);
		}
		return levelNames;
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
