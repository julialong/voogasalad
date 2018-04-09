package data.serialization;

import com.google.gson.Gson;

public class NewSerializer {

	private Gson gson;
	
	public NewSerializer() 
	{
		gson = null;
		addDependencies(gson);
	}
	
	private void addDependencies(Gson gson2) {
//		GsonBuilder builder = new GsonBuilder();
//		builder.registerTypeAdapter(Weapon.class, new WeaponAdapter());
//		gson = builder.create();
		gson = new Gson();
		
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
