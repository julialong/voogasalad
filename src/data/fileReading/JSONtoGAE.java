package data.fileReading;

import java.util.List;
import java.util.Map;

import authoring_environment.game_elements.AuthoredLevel;
import data.resources.DataFileException;

public interface JSONtoGAE {
	
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
	List<AuthoredLevel> loadCompleteAuthoredGame(String gameName) throws DataFileException;
	
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
	AuthoredLevel loadAuthoredGameLevel(String gameName, String levelName) throws DataFileException;

	/**
	 * This will load one of the stray levels that an author can
	 * add to his or her game in the game authoring environment.
	 * 
	 * @param levelName
	 * @return
	 * @throws DataFileException 
	 */
	AuthoredLevel loadAuthoredLevel(String levelName) throws DataFileException;
	
	/**
	 * This will load the names of all of the stray levels so that a user in the authoring
	 * environment can load a pre-existing level.
	 * 
	 * @return
	 */
	List<String> loadAuthoredLevelNames();
	
	/**
	 * This returns a Map of the names of the games for continued editing.
	 * 
	 * @return
	 * @throws DataFileException 
	 */
	Map<String,String> getGameNames() throws DataFileException;

}
