package data.fileReading;

import java.util.List;
import java.util.Map;

import engine.level.Level;

/**
 * @author Belanie Nagiel
 * 
 * Interface for interactions between Game Authoring Environment, Game Player
 * and Game Data. Used for loading existing data files for games.
 *
 */
public interface JSONtoObject {
	
	/**
	 * This method will return the list of Level objects created from the level
	 * files for a particular game. This will locate the folder for the specific
	 * game and load the different level files associated with that folder.
	 * 
	 * @param gameName
	 * @return 
	 */
	@Deprecated
	List<Level> loadCompleteGame(String gameName);

	/**
	 * This will load a specific level within a game based on the game name 
	 * and the name of the level. Will return the Level object associated with 
	 * that level file.
	 * 
	 * @param gameName
	 * @param levelName
	 * @return
	 */
	@Deprecated
	Level loadLevel(String gameName, String levelName);

	/**
	 * This will load the different settings associated with a game, i.e the 
	 * description of the game and whether or not it is ready to be played.
	 * Returns a map of the setting to the value.
	 * 
	 * @param gameName
	 * @return
	 */
	@Deprecated
	Map<String,String> loadSettings(String gameName);

	/**
	 * This returns a Map of the names of the ready to play games and their
	 * descriptions.
	 * 
	 * @return
	 */
	@Deprecated
	Map<String,String> getGameNames();

}
