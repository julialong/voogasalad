package data.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import engine.behavior.Behavior;
import engine.movement.Movement;
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
		builder.registerTypeAdapter(Movement.class, new InterfaceAdapter<Movement>());
		builder.registerTypeAdapter(Behavior.class, new InterfaceAdapter<Behavior>());
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
