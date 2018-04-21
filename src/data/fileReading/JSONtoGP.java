package data.fileReading;

import java.util.List;
import java.util.Map;

import data.resources.DataFileException;
import engine.level.Level;

public interface JSONtoGP {
	
	/**
	 * This method will return the list of Level objects created from the level
	 * files for a particular game. This will locate the folder for the specific
	 * game and load the different level files associated with that folder.
	 * 
	 * @param gameName
	 * @return 
	 * @throws DataFileException 
	 */
	List<Level> loadCompleteGame(String gameName) throws DataFileException;

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
	Level loadLevel(String gameName, String levelName) throws DataFileException;

	/**
	 * This will load the different settings associated with a game, i.e the 
	 * description of the game and whether or not it is ready to be played.
	 * Returns a map of the setting to the value.
	 * 
	 * @param gameName
	 * @return
	 */
	Map<String,String> loadSettings(String gameName);

	/**
	 * This returns a Map of the names of the ready to play games and their
	 * descriptions.
	 * 
	 * @return
	 */
	Map<String,String> getGameNames();
	
	//Something with players?

}
