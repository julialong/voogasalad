package serialization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maya messinger
 * @author Belanie Nagiel
 * Started: 3 Apr 18
 * Class that facilitates addition and removal of items in the levels of a game.
 * Creates appropriate folders and files based on whether the game exists or 
 * not and connects to the GameFile object.
 */
public class GameFileWriter implements GAEtoJSON, GEtoJSON	{
	private static String curlyBracketOpen = "{";
	private static String curlyBracketClose = "}";
	private static String bracketOpen = "[";
	private static String bracketClose = "]";
	private static String colon = ":";
	private static String comma = ",";
	private static String quote = "\"";
	private static String writeErrorStatement = "Could not write to file";

	private String gameDirectory;
	private File gameDirectoryFile;
	private Serializer ser = new Serializer();
	private List<Object> objsToWrite = new ArrayList<>();

	/**
	 * Class Constructor.
	 * Creates or loads the appropriate GameFile object for a game.
	 * @param gameName
	 */
	public GameFileWriter(String gameName)	{
		gameDirectory = "./data/gameData/" + gameName;
		gameToEdit = retrieveGame();
	}

	/**
	 * Iterates through all levels and items in levels passed and updates gameToEdit file to reflect adds, removals, changes
	 * @param changes	editedItems is List<List<item>>. editedItems contains List<item> addedItems, List<item> changedItems, List<item> removedItems
	 */
	@Override
	public void update(Map<Level, List<List<GameObject>>> changes)	{
		try	{
			for (Level aLevel:changes.keySet())	{
				FileWriter fw = new FileWriter(getLevel(1));	// get number from level
				List<List<GameObject>> filesToEdit = changes.get(aLevel);
			
				startFile(fw);

				changetoWrite(fw, filesToEdit.get(1), filesToEdit.get(2), filesToEdit.get(0));
				removetoWrite(fw, filesToEdit.get(2));
				addtoWrite(fw, filesToEdit.get(0));
			}

			endFile(fw);
		}
		catch (IOException e)	{
			error(e);
		}
	}

	/**
	 * Saves state of level being played, for use with checkpoints
	 * @param level			name of level to save
	 * @param itemsInLevel	List (potentially list of lists of different types of objects) if items in level to save stats of
	 */
	@Override
	public void saveData(Level level, List itemsInLevel)	{
	}

	/**
	 * Cancels any edits made to a game since last save
	 * @param gameName	naem fo game to chancel changes to
	 */
	@Override
	public List<Object> revertChanges(String gameName)	{
		return null;
	}
	
	private File retrieveGame()	{
		File gameFolder = new File(gameDirectory);
		if (!gameExists(gameFolder))
		{
			gameFolder.mkdir();
		}
		return gameFolder;
	}

	private boolean gameExists(File gameFolder)	{
		if (!gameFolder.exists() && !gameFolder.isDirectory())	{
			return false;
		}
		return true;
	}

	/**
	 * Accesses the game level file and creates it if necessary.
	 * 
	 * @param levelNumber
	 * @return the level JSON file
	 */
	public File getLevel(int levelNumber)
	{
		File newLevel = new File(gameDirectory + "Level_" + Integer.toString(levelNumber) + ".json");
		if(!newLevel.exists()) { 
			try {
				newLevel.createNewFile();
				writeToLevel(levelNumber,"{\n");
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return newLevel;
	}

	private Map<String, List<Object>> sortObjects(List<GameObject> objsToWrite)	{
		Map<String, List<Object>> objsOrganized = new HashMap<>();	

		for (Object obj:objsToWrite)	{
			String oc = obj.getClass().getSimpleName();

			if (!objsOrganized.containsKey(oc))	{
				objsOrganized.put(oc, new ArrayList<Object>());
			}
			objsOrganized.get(oc).add(obj);
		}

		return objsOrganized;
	}

	private void changetoWrite(FileWriter fw, List<GameObject> items, List<GameObject> toRemove, List<GameObject> toAdd)	{
		for (GameObject item:items)	{
			removetoWrite(fw, item, toRemove);
			toAdd.add(item);
		}
	}

	private void removetoWrite(FileWriter fw, List<GameObject> items)	{
		for (GameObject item:items)	{
			// find item by id
			// remove item
		}
	}

	private void removetoWrite(FileWriter fw, GameObject item, List<GameObject> toRemove)	{
		// find item by id
		// remove
	}

	private void addtoWrite(FileWriter fw, List<GameObject> items)	{
		int entryIndex = 0;
		Map<String, List<Object>> objsOrganized = sortObjects(items);
		for (Map.Entry entry:objsOrganized.entrySet())	{
			String aClass = (String)entry.getKey();
			List<Object> classObjects = (List)entry.getValue();

			startArray(fw, aClass);
			writeArray(fw, classObjects);
			closeArray(fw, entryIndex, objsOrganized.entrySet().size());
			entryIndex++;
		}
	}

	private void startArray(FileWriter fw, String title)	{
		try	{
			fw.write(quote + title + quote + colon + bracketOpen);
			fw.write(System.lineSeparator());
		}
		catch (IOException e)	{
			error(e);
		}
	}

	private void closeArray(FileWriter fw, int entryIndex, int mapSize)	{
		try	{
			fw.write(bracketClose);
			checkWriteComma(fw, entryIndex, mapSize);
		}
		catch (IOException e)	{
			error(e);
		}
	}

	private void writeArray(FileWriter fw, List toWrite)	{
		try	{
			for (Object obj:toWrite)	{
				fw.write(ser.serialize(obj));
				checkWriteComma(fw, toWrite.indexOf(obj), toWrite.size());
				fw.write(System.lineSeparator());
			}
		}
		catch (IOException e)	{
			error(e);
		}
	}

	private void checkWriteComma(FileWriter fw, int index, int size)	{
		if (index < size - 1)	{
			try	{
				fw.write(comma);
			}
			catch (IOException e)	{
				error(e);
			}
		}
	}

	private void startFile(FileWriter fw)	{
		try	{
			fw.write(curlyBracketOpen);
		}
		catch (IOException e)	{
			error(e);
		}
	}

	private void endFile(FileWriter fw)	{
		try	{
			fw.write(curlyBracketClose);
			fw.close();
		}
		catch (IOException e)	{
			error(e);
		}
	}

	private void error(IOException e)	{
		System.out.println("Could not write to file");
	}
}