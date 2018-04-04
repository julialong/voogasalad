package data.objtodata;

import java.util.Map;
import java.util.List;

import serialization.GAEtoJSON;
import serialization.GEtoJSON;
import serialization.Serializer;

/**
 * @author Maya Messinger
 * Class that converts objects in a game authoring environment or game engine to JSON data objects
 */
public class ObjectConverter implements GAEtoJSON, GEtoJSON	{
	Serializer ser = new Serializer();

	public void update(Map<Level, List<List<GameObject>>> changes)	{
		for (Level aLevel:changes.keySet())	{
			List<List<GameObject>> filesToEdit = changes.get(aLevel);

			for (GameObject added:filesToEdit.get(0))	{
				// add new item to database
			}
			for (GameObject changed:filesToEdit.get(1))	{
				// find item in database by id, update it with new properties
			}
			for (GameObject removed:filesToEdit.get(2))	{
				// find item in database by id, remove it from database
			}
		}
	}

	public void saveData(Level level, List itemsInLevel)	{
	}

	public List<Object> revertChanges(String gameName)	{
		return null;
	}

	public void loadNewGame(String gameName)	{
		if (!gameExists(gameName))	{
			// make new folder called gameName
			// add template files to game folder
		}

		// JSONtoObject.loadExisting(gameName);
	}

	private boolean gameExists(String gameName)	{
		// if directory iwth name
		if (true)	{
			return true;
		}
		else	{
			return false;
		}
	}
}