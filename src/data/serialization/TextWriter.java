package data.serialization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maya Messinger
 * Started 5 Apr 17
 * Class that copletely encapsulates file writing
 */
public class TextWriter	{
	private static String curlyBracketOpen = "{";
	private static String curlyBracketClose = "}";
	private static String bracketOpen = "[";
	private static String bracketClose = "]";
	private static String colon = ":";
	private static String comma = ",";
	private static String quote = "\"";
	private static String writeErrorStatement = "Could not write to file";

	private Serializer ser = new Serializer();

	/**
	 * @author Maya Messinger
	 * Constructor for class. Calls the writing, so making a new TextWriter writes to a file
	 * @param level			File of level to write
	 * @param itemsInLevel	items to serialize and write
	 */
	public TextWriter(File level, List itemsInLevel)	{
		callWrite(level, itemsInLevel);
	}

	private void callWrite(File level, List<GameObject> itemsInLevel)	{
		try	{
			FileWriter fw = new FileWriter(level);	// get number from level
		
			startFile(fw);
			addtoWrite(fw, itemsInLevel);
			endFile(fw);
		}
		catch (IOException e)	{
			error(e);
		}
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

	private void startFile(FileWriter fw)	{
		try	{
			fw.write(curlyBracketOpen);
			fw.write(System.lineSeparator());
		}
		catch (IOException e)	{
			error(e);
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
			fw.write(System.lineSeparator());
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