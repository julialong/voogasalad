package data_serialization;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class GameFileReader {

	public String gameDirectory;
	
	
	public GameFileReader(String gameName)
	{
		gameDirectory = "./data/gameData/" + gameName;
	}
	
	public List<Object> loadGame()
	{
		File currentGame = new File("testJson.json"); 
		try {
			JsonReader jsonReader = new JsonReader(new FileReader(currentGame));
			jsonReader.beginObject();
			String name = jsonReader.nextName();
			System.out.print(name);
			jsonReader.beginArray();
			jsonReader.beginObject();
			Gson g = new Gson();
			
//			String n = jsonReader.nextName();
//			System.out.print(n);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
