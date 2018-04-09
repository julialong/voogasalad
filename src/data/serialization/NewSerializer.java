package data.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import engine.weapon.Weapon;

public class NewSerializer {

	private Gson gson;
	
	public NewSerializer() 
	{
		gson = null;
		addDependencies(gson);
	}
	
	private void addDependencies(Gson gson2) {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Weapon.class, new InterfaceAdapter<Weapon>());
		gson = builder.create();	
	}
	
	public String serialize(Object obj)	
	{
        return gson.toJson(obj);
	}

	public Object deserialize(String json, Class<?> objectClass)
	{
		return gson.fromJson(json, objectClass);
	}	
}
