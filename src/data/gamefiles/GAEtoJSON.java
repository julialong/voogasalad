package data.gamefiles;

import authoring_environment.game_elements.AuthoredLevel;
import data.resources.DataFileException;
import engine.level.Level;

import java.util.List;

/**
 * From information given to this class from GAE, parses GAE classes and objects into data to push to database
 */
public interface GAEtoJSON	{
	/**
	 * Called by GAE, updates database with changes from last time GAE saved data
	 * Data passed are levels or settings (corresponding to files), and a list of the items per file to update
	 * @param changes	list of levels to update
	 */
	void update(List<AuthoredLevel> changes) throws DataFileException;

	/**
	 * Will change settings file mapping to whether game is ready to play or not
	 * @param ready		value to change readiness of game to
	 * @param desc		new description of game
	 */
	void updateMeta(boolean ready, String desc) throws DataFileException;

	/**
	 * Will change settings file mapping to whether game is ready to play or not
	 * @param ready			value to change readiness of game to
	 * @param desc			new description of game
	 * @param levelStart	level to start at
	 */
	void updateMeta(boolean ready, String desc, int levelStart) throws DataFileException;

	/**
	 * Method that allows for changes to be reverted to last saved version of game, if user decides to entirely scrap their updats
	 * @param level	level to revert info for
	 */
	@Deprecated
	Level revertChanges(Level level) throws DataFileException;

	/**
	 * Method that allows for changes to be reverted to last saved version of game, if user decides to entirely scrap their updats
	 * @param level	level to revert info for
	 */
	AuthoredLevel revertChanges(AuthoredLevel level) throws DataFileException;

	/**
	 * Method to rename a game (folder)
	 * @param newName	String to rename game to	
	 */
	void renameGame(String newName) throws DataFileException;

	/**
	 * Allows for saving individual level as a "stray", not part of a game
	 * @param level		level to save separately
	 */
	public void saveIndivLevel(AuthoredLevel level) throws DataFileException;
}