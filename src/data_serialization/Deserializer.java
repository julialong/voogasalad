package data_serialization;

import com.google.gson.Gson;

public class Deserializer {
	
	private Gson gson;
	
	public Deserializer() 
	{
		gson = new Gson();
	}
	
	public Object deserialize(String json, Class<?> objectClass)
	{
		return gson.fromJson(json, objectClass);
	}
	
	
}
