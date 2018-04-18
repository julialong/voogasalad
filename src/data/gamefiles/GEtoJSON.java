package data.gamefiles;

import java.util.List;

import engine.entity.GameEntity;
import engine.level.Level;

/**
 * From information given to this class from Game Engine, parses Engine classes and objects into data to push to database to create effectively a "save game" functionality
 */
public interface GEtoJSON	{
	/**
	 * Called by Game Engine, updates database with data as it is currently in the play environment
	 * Only need to save state of current level, because player will either have completed past level (making it unnecessary to save)
	 * or has not reached further level (unnecessary to save as new file, file exists in game data already)
	 * @param player		name of player
	 * @param levels		list of levels to save
	 */
	void saveData(String player, List<Level> levels);
		// for (List items:itemsInLevel)	{
		// 	for (Object item:items)	{
		// 		// push item and its info to database
		// 	}
		// }
}