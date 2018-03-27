import java.util.Map;
import java.util.List;

/**
 * From information given to this class from GAE, parses GAE classes and objects into data to push to database
 */
public interface GAEtoJSON	{
	/**
	 * Called by GAE, updates database with changes from last time GAE saved data
	 * Data passed are levels or settings (corresponding to files), and a list of the items per file to update
	 * @param changes	editedItems is List<List<item>>. editedItems contains List<item> addedItems, List<item> changedItems, List<item> removedItems
	 */
	public void update(Map<filesToEdit, editedItems> changes)	{
		for (Object file:filesToEdit)	{
			for (Object added:editedItems.get(0))	{
				// add new item to database
			}
			for (Object changed:editedItems.get(1))	{
				// find item in database by id, update it with new properties
			}
			for (Object removed:editedItems.get(2))	{
				// find item in database by id, remove it from database
			}
		}
	}

	/**
	 * GAE calls when user starts editing a game. Makes a new game folder (with template items) if game does not already exist (checks name)
	 * @param gameName	name of game to make
	 */
	public void loadNewGame(String gameName)	{
		if (!gameExists(gameName))	{
			// make new folder called gameName
			// add template files to game folder
		}

		JSONtoGAE.loadExisting(gameName);
	}

	private boolean gameExists(String gameName)	{
		return true;
	}
}