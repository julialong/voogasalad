package data.objtodata;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import serialization.Serializer;

public class Tester	{
	private static Serializer ser = new Serializer();
	private static List<Object> objsToWrite = new ArrayList<>();
	private static Map<String, List<Object>> objsOrganized = new HashMap<>();

	private static String curlyBracketOpen = "{";
	private static String curlyBracketClose = "}";
	private static String bracketOpen = "[";
	private static String bracketClose = "]";
	private static String colon = ":";
	private static String comma = ",";
	private static String quote = "\"";
	private static String writeErrorStatement = "Could not write to file";

	public static void main(String[] args)	{
		makeDummyObjects();

		try	{
			FileWriter fw = new FileWriter("./data/testJson.json");
			
			startFile(fw);

			int entryIndex = 0;
			for (Map.Entry entry:objsOrganized.entrySet())	{
				String aClass = (String)entry.getKey();
				List<Object> classObjects = (List)entry.getValue();

				startArray(fw, aClass);
				writeArray(fw, classObjects);
				closeArray(fw, entryIndex, objsOrganized.entrySet().size());
				entryIndex++;
			}

			endFile(fw);
		}
		catch (IOException e)	{
			error(e);
		}
	}

	private static void makeDummyObjects()	{
		objsToWrite.add(new ExampleObject());
		objsToWrite.add(new ExampleObject2());
		objsToWrite.add(new ExampleObject());
		objsToWrite.add(new ExampleObject2());
		objsToWrite.add(new ExampleObject3());
		objsToWrite.add(new ExampleObject3());

		for (Object obj:objsToWrite)	{
			String oc = obj.getClass().getName();

			if (!objsOrganized.containsKey(oc))	{
				objsOrganized.put(oc, new ArrayList<Object>());
			}
			objsOrganized.get(oc).add(obj);
		}
	}

	private static void startArray(FileWriter fw, String title)	{
		try	{
			fw.write(quote + title + quote + colon + bracketOpen);
			fw.write(System.lineSeparator());
		}
		catch (IOException e)	{
			error(e);
		}
	}

	private static void closeArray(FileWriter fw, int entryIndex, int mapSize)	{
		try	{
			fw.write(bracketClose);
			checkWriteComma(fw, entryIndex, mapSize);
		}
		catch (IOException e)	{
			error(e);
		}
	}

	private static void writeArray(FileWriter fw, List toWrite)	{
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

	private static void checkWriteComma(FileWriter fw, int index, int size)	{
		if (index < size - 1)	{
			try	{
				fw.write(comma);
			}
			catch (IOException e)	{
				error(e);
			}
		}
	}

	private static void startFile(FileWriter fw)	{
		try	{
			fw.write(curlyBracketOpen);
		}
		catch (IOException e)	{
			error(e);
		}
	}

	private static void endFile(FileWriter fw)	{
		try	{
			fw.write(curlyBracketClose);
			fw.close();
		}
		catch (IOException e)	{
			error(e);
		}
	}

	private static void error(IOException e)	{
		System.out.println("Could not write to file");
	}
}