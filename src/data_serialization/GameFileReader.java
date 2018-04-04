package data_serialization;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import data.objtodata.ExampleObject;

public class GameFileReader {

	public String gameDirectory;
	
	
	public GameFileReader(String gameName)
	{
		gameDirectory = "./data/gameData/" + gameName;
	}
	
	public List<Object> loadGame()
	{
		File currentGame = new File("testJson.json"); 
		Gson gson = new Gson();
		try {
//			JsonReader jsonReader = new JsonReader(new FileReader(currentGame));
//			jsonReader.beginObject();
//			String name = jsonReader.nextName();
//			System.out.print(name);
//			jsonReader.beginArray();
//			jsonReader.beginObject();
//			Gson g = new Gson();
//			
//			String n = jsonReader.nextName();
//			System.out.print(n);
			
			JsonParser jsonParser = new JsonParser();
			JsonElement jelement = jsonParser.parse(new FileReader(currentGame));
			JsonObject  jobject = jelement.getAsJsonObject();
			JsonArray jarray = jobject.getAsJsonArray("data.objtodata.ExampleObject");
			System.out.println(jarray);
			for(int i = 0; i < jarray.size(); i++)
			{
				JsonObject j = jarray.get(i).getAsJsonObject();
			    System.out.println(j.toString().getClass());
			    ExampleObject x = gson.fromJson(j.toString(), ExampleObject.class);
			    System.out.println(x);
			    
			}
		    
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
