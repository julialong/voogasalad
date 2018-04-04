package data_serialization;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import data.objtodata.ExampleObject;

public class GameFileReader {

	public String gameDirectory;
	public Map<String,Class<?>> objectTypes = new HashMap<>();
	
	
	public GameFileReader(String gameName)
	{
		gameDirectory = "./data/gameData/" + gameName;
		objectTypes.put("ExampleObject", ExampleObject.class);
		objectTypes.put("SampleObject", SampleObject.class);
	}
	
	
	
	public List<Object> loadGame()
	{
		List<Object> gameObjects = new ArrayList<Object>();
		File currentGame = new File("testJson.json"); 
		Gson gson = new Gson();
		try {
			JsonParser jsonParser = new JsonParser();
			JsonElement jelement = jsonParser.parse(new FileReader(currentGame));
			JsonObject  jobject = jelement.getAsJsonObject();
			for(String s: objectTypes.keySet())
			{
				JsonArray jarray = jobject.getAsJsonArray(s);
				System.out.println(jarray);
				for(int i = 0; i < jarray.size(); i++)
				{
					JsonObject j = jarray.get(i).getAsJsonObject();
				    System.out.println(j.toString().getClass());
				    Object x = gson.fromJson(j.toString(), objectTypes.get(s));
				    System.out.println(x);  
				    gameObjects.add(x);
				}
				
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return gameObjects;
	}
}
