package data.objtodata;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tester	{
	private static Serializer ser = new Serializer();

	private static String curlyBracketOpen = "{";
	private static String curlyBracketClose = "}";
	private static String bracketOpen = "[";
	private static String bracketClose = "]";
	private static String colon = ":";
	private static String comma = ",";
	private static String quote = "\"";

	public static void main(String[] args)	{
		List<Object> objsToWrite = new ArrayList<>();
		Map<String, List<Object>> objsOrganized = new HashMap<>();

		objsToWrite.add(new ExampleObject());
		objsToWrite.add(new ExampleObject2());
		objsToWrite.add(new ExampleObject());
		objsToWrite.add(new ExampleObject2());
		objsToWrite.add(new ExampleObject3());
		objsToWrite.add(new ExampleObject3());

		try	{
			FileWriter fw = new FileWriter("./data/testJson.json");
			
			fw.write(curlyBracketOpen);

			for (Object obj:objsToWrite)	{
				String oc = obj.getClass().getName();

				if (!objsOrganized.containsKey(oc))	{
					objsOrganized.put(oc, new ArrayList<Object>());
				}
				objsOrganized.get(oc).add(obj);
			}

			int entryIndex = 0;
			for (Map.Entry entry:objsOrganized.entrySet())	{
				String aClass = (String)entry.getKey();
				List<Object> classObjects = (List)entry.getValue();

				fw.write(quote + aClass + quote + colon + bracketOpen);
				fw.write(System.lineSeparator());

				for (Object classObject:classObjects)	{
					fw.write(ser.serialize(classObject));
					checkWriteComma(fw, classObjects.indexOf(classObject), classObjects.size());
					fw.write(System.lineSeparator());
				}

				fw.write(bracketClose);
				checkWriteComma(fw, entryIndex, objsOrganized.entrySet().size());
				entryIndex++;
			}

			fw.write(curlyBracketClose);
			fw.close();
		}
		catch (IOException e)	{
			System.out.println("Could not write to file");
		}
	}

	private static void checkWriteComma(FileWriter fw, int index, int size)	{
		if (index < size - 1)	{
			try	{
				fw.write(comma);
			}
			catch (IOException e)	{
				System.out.println("Could not write to file");
			}
		}
	}
}