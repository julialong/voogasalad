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

public class GameFileReader implements JSONtoObject {

	public String gameDirectory;
	public File currentGame;
	public File currentLevel;
	public Map<String,Class<?>> objectTypes = new HashMap<>();
	public Deserializer deserializer;
	
	public GameFileReader()
	{
		objectTypes.put("ExampleObject", ExampleObject.class);
		objectTypes.put("SampleObject", SampleObject.class);
		deserializer = new Deserializer();
	}
	
	private void retrieveCurrentGame(String gameName)
	{
		gameDirectory = "./data/gameData/" + gameName;
		currentGame = new File("testJson.json"); 
	}
	
	private void retrieveLevel(String gameName, String level)
	{
		retrieveCurrentGame(gameName);
		currentLevel = new File(gameDirectory + "/" + level);
	}
	
	@Override
	public List<Object> loadGame(String gameName) {
		retrieveCurrentGame(gameName);
		List<Object> gameObjects = new ArrayList<Object>();
		try {
			JsonParser jsonParser = new JsonParser();
			JsonElement jelement = jsonParser.parse(new FileReader(currentGame));
			JsonObject  jobject = jelement.getAsJsonObject();
			for(String objectType: objectTypes.keySet())
			{
				JsonArray jarray = jobject.getAsJsonArray(objectType);
				System.out.println(jarray);
				for(int i = 0; i < jarray.size(); i++)
				{
					gameObjects.add(convertToObject(jarray.get(i).getAsJsonObject(), objectType));
				}
				
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return gameObjects;
	}
	
	private Object convertToObject(JsonObject toConvert, String objectType)
	{
		JsonObject j = toConvert;
	    System.out.println(j.toString().getClass());
	    Object converted = deserializer.deserialize(j.toString(), objectTypes.get(objectType));
	    System.out.println(converted); 
		return converted;
	}

	@Override
	public List<Object> loadLevel(String gameName, String levelName) {
		retrieveLevel(gameName, levelName);
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Object> loadSettings(String gameName) {
		// TODO Auto-generated method stub
		return null;
	}
}


