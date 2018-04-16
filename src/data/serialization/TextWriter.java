package data.serialization;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring_environment.game_elements.AuthoredLevel;
import engine.entity.GameEntity;
import engine.level.Level;

/**
 * @author Maya Messinger
 * Started 5 Apr 17
 * Class that copletely encapsulates file writing
 */
public class TextWriter	{
	public static final String CURLYBRACKETOPEN = "{";
	public static final String CURLYBRACKETCLOSE = "}";
	public static final String BRACKETOPEN = "[";
	public static final String BRACKETCLOSE = "]";
	public static final String COLON = ":";
	public static final String COMMA = ",";
	public static final String QUOTE = "\"";
	private static final String WRITEERRORSTATEMENT = "Could not write to file";

	private static final String DESCRIPTION = "description";
	private static final String READYTOPLAY = "readyToPlay";

	private Serializer ser = new Serializer();

	/**
	 * @author Maya Messinger
	 * Constructor for class for use with writing settings file (no objects)
	 * @param settings	File of settings to edit
	 * @param ready		whether game is ready or not
	 * @param desc		description of game
	 */
	public TextWriter(File settings, boolean ready, String desc)	{
		callWrite(settings, ready, desc);
	}

	/**
	 * @author Maya Messinger
	 * Constructor for class. Calls the writing, so making a new TextWriter writes to a file
	 * @param level			File of level to write
	 */
	public TextWriter(AuthoredLevel level, File levelF)	{
		callWrite(level, levelF);
	}

	private void callWrite(File settings, boolean ready, String desc)	{
		try	{
			FileWriter fw = new FileWriter(settings);
		
			startFile(fw);
			writeSettings(fw, ready, desc);
			endFile(fw);
		}
		catch (IOException e)	{
			error(e);
		}
	}

	private void callWrite(AuthoredLevel level, File levelF)	{
		try	{
			FileWriter fw = new FileWriter(levelF);
		
			startFile(fw);
			serializeLevel(fw, level);
			writeObjects(fw, level.getLevel().getObjects());
			endFile(fw);
		}
		catch (IOException e)	{
			error(e);
		}
	}

	private void writeSettings(FileWriter fw, boolean ready, String desc)	{
		try	{
			fw.write(QUOTE + DESCRIPTION + QUOTE + COLON + QUOTE + desc + QUOTE);
			fw.write(COMMA);
			newLine(fw);
			fw.write(QUOTE + READYTOPLAY + QUOTE + COLON + ready);
			newLine(fw);
		}
		catch (IOException e)	{
			error(e);
		}
	}

	private void serializeLevel(FileWriter fw, AuthoredLevel level)	{
		new LevelSerializer().serialize(fw, level.getLevel(), level.getScrollingGrid());
	}

	private void writeObjects(FileWriter fw, List<GameEntity> items)	{
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

	private Map<String, List<Object>> sortObjects(List<GameEntity> objsToWrite)	{
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

	protected static void startFile(FileWriter fw)	{
		try	{
			fw.write(CURLYBRACKETOPEN);
			newLine(fw);
		}
		catch (IOException e)	{
			error(e);
		}
	}

	private static void endFile(FileWriter fw)	{
		try	{
			fw.write(CURLYBRACKETCLOSE);
			fw.close();
		}
		catch (IOException e)	{
			error(e);
		}
	}

	protected static void startArray(FileWriter fw, String title)	{
		try	{
			if (title != null && !title.equals(""))	{
				writeKey(fw, title);
			}

			fw.write(BRACKETOPEN);
			newLine(fw);
		}
		catch (IOException e)	{
			error(e);
		}
	}

	protected static void closeArray(FileWriter fw, int entryIndex, int mapSize)	{
		try	{
			fw.write(BRACKETCLOSE);
			checkWriteComma(fw, entryIndex, mapSize);
			newLine(fw);
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
				newLine(fw);
			}
		}
		catch (IOException e)	{
			error(e);
		}
	}

	protected static void newLine(FileWriter fw)	{
		try	{
			fw.write(System.lineSeparator());
		}
		catch(IOException e)	{
			error(e);
		}
	}

	protected static void writeKey(FileWriter fw, String key)	{
		try	{
			fw.write(QUOTE + key + QUOTE + COLON);
		}
		catch (IOException e)	{
			error(e);
		}
	}

	protected static void writeValue(FileWriter fw, String value)	{
		try	{
			fw.write(QUOTE + value + QUOTE);
		}
		catch (IOException e)	{
			error(e);
		}
	}

	protected static void writeValue(FileWriter fw, int value)	{
		try	{
			fw.write(Integer.toString(value));
		}
		catch (IOException e)	{
			error(e);
		}
	}

	protected static void checkWriteComma(FileWriter fw, int index, int size)	{
		if (index < size - 1)	{
			try	{
				fw.write(COMMA);
			}
			catch (IOException e)	{
				error(e);
			}
		}
	}

	protected static void error(IOException e)	{
		System.out.println(WRITEERRORSTATEMENT);
	}
}