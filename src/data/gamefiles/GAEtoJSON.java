package data.gamefiles;

import java.util.Map;

import engine.entity.GameEntity;
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
	void update(List<Level> changes);

	/**
	 * Method that allows for changes to be reverted to last saved version of game, if user decides to entirely scrap their updats
	 * @param level	level to revert info for
	 */
	List<Object> revertChanges(Level level);

	/**
	 * Method to rename a game (folder)
	 * @param newName	String to rename game to	
	 */
	void renameGame(String newName);
}