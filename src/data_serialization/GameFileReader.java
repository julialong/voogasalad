package data_serialization;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class GameFileReader implements JSONtoObject {

	private String gameDirectory;
	private File currentGame;
	private File currentLevel;
	private Map<String,Class<?>> objectTypes = new HashMap<>();
	private Deserializer deserializer;
	
	public GameFileReader()
	{
//		objectTypes.put("ExampleObject", ExampleObject.class);
//		objectTypes.put("SampleObject", SampleObject.class);
		createObjectToClassMap();
		deserializer = new Deserializer();
	}
	
	private void createObjectToClassMap()
	{
		ResourceBundle gameObjects = ResourceBundle.getBundle("data_serialization/gameObjects");
		Enumeration<String> objectNames = gameObjects.getKeys();
		while(objectNames.hasMoreElements())
		{
			String objectName = objectNames.nextElement();
			try {
				Class<?> objectClass = Class.forName(gameObjects.getString(objectName));
				objectTypes.put(objectName, objectClass);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		Enumeration<String> keys = language.getKeys();
//		while(keys.hasMoreElements())
//		{
//			String key = keys.nextElement();
//			map.put(key, Pattern.compile(language.getString(key), Pattern.CASE_INSENSITIVE));
//		}
		
	}
	
	private void retrieveCurrentGame(String gameName)
	{
		gameDirectory = "./data/gameData/" + gameName;
		currentGame = new File("./data/gameData/testJson.json"); 
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


