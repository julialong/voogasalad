package serialization;

import java.util.Map;

import data.objtodata.Level;

import java.util.List;

/**
 * From information given to this class from Game Engine, parses Engine classes and objects into data to push to database to create effectively a "save game" functionality
 */
public interface GEtoJSON	{
	/**
	 * Called by Game Engine, updates database with data as it is currently in the play environment
	 * Only need to save state of current level, because player will either have completed past level (making it unnecessary to save)
	 * or has not reached further level (unnecessary to save as new file, file exists in game data already)
	 * @param level			name of level to save
	 * @param itemsInLevel	List (potentially list of lists of different types of objects) if items in level to save stats of
	 */
	void saveData(Level level, List itemsInLevel);
		// for (List items:itemsInLevel)	{
		// 	for (Object item:items)	{
		// 		// push item and its info to database
		// 	}
		// }
}