package serialization;

import java.util.Map;
import java.util.List;

/**
 * From information given to this class from GAE, parses GAE classes and objects into data to push to database
 */
public interface GAEtoJSON	{
	/**
	 * Called by GAE, updates database with changes from last time GAE saved data
	 * Data passed are levels or settings (corresponding to files), and a list of the items per file to update
	 * @param changes	editedItems is a list of all GameObjects currently in authoring environment
	 */
	void update(Map<Level, List<GameObject>> changes);

	/**
	 * Method that allows for changes to be reverted to last saved version of game, if user decides to entirely scrap their updats
	 * @param gameName		name of game to load past save of
	 */
	List<Object> revertChanges(Level level);
}