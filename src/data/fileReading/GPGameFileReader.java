package data.fileReading;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.resources.DataFileException;
import engine.level.Level;
import data.levelBuilders.LevelBuilder;
/**
 *  This class holds the implementation for the methods that allow the Game Player to load games and files
 * for play.
 * 
 * @author Belanie Nagiel
 *
 */
public class GPGameFileReader extends GameFileReader implements JSONtoGP{
	/**
	 * Class Constructor
	 */
	public GPGameFileReader(){}
	
	/**
	 * This method will return the list of Level objects created from the level
	 * files for a particular game. This will locate the folder for the specific
	 * game and load the different level files associated with that folder.
	 * 
	 * @param gameName
	 * @return 
	 * @throws DataFileException 
	 */
	@Override
	public List<Level> loadCompleteGame(String gameName) throws DataFileException {
		List<Level> completeGame = new ArrayList<>();
		File currentGame = new File(getCurrentGamePath(gameName));
		File[] gameFiles = currentGame.listFiles();
		for(File gameFile: gameFiles)
		{
			addLevel(gameFile,completeGame, gameName);	
		}
		completeGame = orderLevels(gameName, completeGame);
		return completeGame;
	}
	
	/**
	 * Adds a level to the complete game based on the level file.
	 * 
	 * @param gameFile
	 * @param completeGame
	 * @param gameName
	 * @throws DataFileException
	 */
	private void addLevel(File gameFile, List<Level> completeGame, String gameName) throws DataFileException {
		if(!gameFile.isDirectory())
		{
			int index = gameFile.toString().lastIndexOf(NEST) + 1;
			int endIndex = gameFile.toString().lastIndexOf(JSON_EXTENSION);
			String levelName = gameFile.toString().substring(index,endIndex).trim();
			if(!levelName.equals(SETTINGS) && !levelName.equals(LEVEL_ORDER))
			{
				completeGame.add(loadLevel(gameName, levelName));
			}
		}		
		
	}

	/**
	 * Re-orders the list of levels in loadCompleteGame in order to match the LevelOrder file
	 * 
	 * @param gameName
	 * @param levels
	 * @return
	 * @throws DataFileException
	 */
	private List<Level> orderLevels(String gameName, List<Level> levels) throws DataFileException
	{
		Map<String,Integer> levelOrder = getLevelOrder(gameName);
		Level[] orderedLevels = new Level[levels.size()];
		for(Level level: levels)
		{
			int position = levelOrder.get(level.getName());
			orderedLevels[position] = level;
		}
		return Arrays.asList(orderedLevels);
	}
	
	/**
	 * This will load a specific level within a game based on the game name 
	 * and the name of the level. Will return the Level object associated with 
	 * that level file.
	 * 
	 * @param gameName
	 * @param levelName
	 * @return
	 * @throws DataFileException 
	 */
	@Override
	public Level loadLevel(String gameName, String levelName) throws DataFileException {
		File currentLevel = getLevel(gameName, levelName);
		LevelBuilder levelBuilder = new LevelBuilder(currentLevel);
		return levelBuilder.buildLevel();
	}

	/**
	 * This returns a Map of the names of the ready to play games and their
	 * descriptions.
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
			if(Boolean.parseBoolean(gameSettings.get(READY)))
			{
				gameNames.put(gameName, gameSettings.get(DESCRIPTION));
			}
		}
		return gameNames;
	}
}
