package data.objtodata;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.stream.JsonReader;

public class Tester	{
	private static Serializer ser = new Serializer();

	public static void main(String[] args)	{
		List<ExampleObject> objsToWrite = new ArrayList<>();
		List<String> toWrite = new ArrayList<>();
		List<String> objTypes = new ArrayList<>();

		objsToWrite.add(new ExampleObject());
		objsToWrite.add(new ExampleObject());
		objsToWrite.add(new ExampleObject());

		try	{
			FileWriter fw = new FileWriter("testJson.json");
			
			fw.write("{");

			int insertAt = 0;
			for (ExampleObject obj:objsToWrite)	{
				if (!objTypes.contains(obj.type))	{
					objTypes.add(obj.type);
					toWrite.add("\"" + obj.type + "\":[");
					toWrite.add("]");
					insertAt =  + 1;
				}

				toWrite.add(insertAt, ser.serialize(obj));
			}

			for (String text:toWrite)	{
				fw.write(text);

				// if ()	{
				// }

				fw.write(System.lineSeparator());
			}

			fw.write("}");
			fw.close();
		}
		catch (IOException e)	{
			System.out.println("Could not write to file");
		}
	}
}