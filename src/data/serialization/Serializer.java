package data.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import engine.behavior.Behavior;
import engine.movement.Movement;
import engine.weapon.Weapon;
/**
 * @author Belanie Nagiel
 * @author Maya Messinger
 * 
 * Adds the appropriate interface adapters to the Gson serializer, serializes
 * Objects and deserializes JSON strings.
 *
 */
public class Serializer {

	private Gson gson;
	
	/**
	 * Class Constructor
	 * Adds appropriate interface adapters.
	 */
	public Serializer() 
	{
		gson = null;
		addDependencies(gson);
	}
	
	/**
	 * Adds all the relevant interface adapters to the 
	 * Gson Serializer.
	 * 
	 * @param Gson object
	 */
	private void addDependencies(Gson gson2) {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Weapon.class, new InterfaceAdapter<Weapon>());
		builder.registerTypeAdapter(Movement.class, new InterfaceAdapter<Movement>());
		builder.registerTypeAdapter(Behavior.class, new InterfaceAdapter<Behavior>());
		gson = builder.create();	
	}
	
	/**
	 * Returns the JSON string for the given object
	 * 
	 * @param obj
	 * @return
	 */
	public String serialize(Object obj)	
	{
        return gson.toJson(obj);
	}

	/**
	 * Returns the appropriate object based on the JSON string and object
	 * class.
	 * 
	 * @param json
	 * @param objectClass
	 * @return
	 */
	public Object deserialize(String json, Class<?> objectClass)
	{
		return gson.fromJson(json, objectClass);
	}	
}
