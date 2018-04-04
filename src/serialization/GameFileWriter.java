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
 * @author Belanie Nagiel
 * 
 * Class that facilitates addition and removal of items in the levels of a game.
 * Creates appropriate folders and files based on whether the game exists or 
 * not and connects to the GameFile object.
 *
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
	private File gameToEdit;
	private Serializer ser = new Serializer();
	private List<Object> objsToWrite = new ArrayList<>();

	/**
	 * Class Constructor.
	 * Creates or loads the appropriate GameFile object for a game. 
	 * 
	 * @param gameName
	 */
	public GameFileWriter(String gameName)	{
		gameDirectory = "./data/gameData/" + gameName;
		gameToEdit = retrieveGame();
	}

	public void update(Map<Level, List<List<GameObject>>> changes)	{
		try	{
			FileWriter fw = new FileWriter(gameToEdit);
			
			startFile(fw);

			for (Level aLevel:changes.keySet())	{
				List<List<GameObject>> filesToEdit = changes.get(aLevel);

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

	public void saveData(Level level, List itemsInLevel)	{
	}

	public void loadNewGame(String gameName)	{
	}

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