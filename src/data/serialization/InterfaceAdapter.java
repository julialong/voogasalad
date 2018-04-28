package data.serialization;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import data.resources.DataFileException;
import engine.behavior.MoveForward;
/**
 * @author Stack Overflow Post, Belanie Nagiel
 * 
 * This class was made with the help of the post: 
 * https://stackoverflow.com/questions/4795349/how-to-serialize-a-class-with-an-interface/9550086#9550086 
 * 
 * This class helps with the serialization and deserialization of instances of interfaces. Interfaces cannot
 * be deserialized normally because they have no constructors.
 *
 * @param <T> The interface that needs to be taken into account
 */
public class InterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
	
	private static final String BEHAVIOR_SKIPS = "data.resources/behaviorsToSkip";
	
	@Override
	/**
	 * Adds a wrapper around the Interface to indicate that it must be deserialized a different way
	 * when read back in.
	 */
	public JsonElement serialize(T arg0, Type arg1, JsonSerializationContext arg2) {
		JsonObject wrapper = new JsonObject();
		wrapper.addProperty("type", arg0.getClass().getName());
		wrapper.add("data", new Gson().toJsonTree(arg0));
		return wrapper;
	}
	
	@Override
	/**
	 * Converts the interface object into its appropriate game entity.
	 */
	public T deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		List<String> behaviorsToSkip = buildBehaviorSkipMap();
		JsonObject wrapper = (JsonObject) arg0;
		System.out.println(wrapper);
		JsonElement typeName = get(wrapper, "type");
		if(behaviorsToSkip.contains(typeName.getAsString()))
		{
			return getEmptyBehavior(typeName.getAsString());
			
//			return (T) 
		}
		if(typeName.toString().equals("\"engine.behavior.MoveForward\""))
		{
			System.out.println("I GOT HERE");
			return (T) new MoveForward();
		}
		JsonElement data = get(wrapper, "data");
		Type actualType = typeForName(typeName);
		return arg2.deserialize(data, actualType);
	}

	private T getEmptyBehavior(String behaviorType) {
		try 
		{
			Class behaviorClass = Class.forName(behaviorType);
			Constructor<?> c = behaviorClass.getConstructor();
			c.setAccessible(true);
			Object o = c.newInstance();
			return (T)o;
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			throw new JsonParseException("Could not create ");
		}
		
	}

	private List<String> buildBehaviorSkipMap()
	{
		List<String> behaviorsToSkip = new ArrayList<>();
		ResourceBundle behaviors = ResourceBundle.getBundle(BEHAVIOR_SKIPS);
		Enumeration<String> behaviorNames = behaviors.getKeys();
		while(behaviorNames.hasMoreElements())
		{
			String behaviorName = behaviorNames.nextElement();
			behaviorsToSkip.add(behaviors.getString(behaviorName));
		}
		return behaviorsToSkip;
	}

	/**
	 * 
	 * Gets the type of the object based on its type name.
	 * 
	 * @param typeName
	 * @return Object type
	 */
	private Type typeForName(JsonElement typeName) {
		try {
            return Class.forName(typeName.getAsString());
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
	}

	/**
	 * Returns a json element based on the Json Object and the 
	 * string associated with the data.
	 * 
	 * @param wrapper
	 * @param string
	 * @return
	 */
	private JsonElement get(JsonObject wrapper, String string) {
		if(!wrapper.toString().contains(string))
		{
			System.out.println("hello");
		}
		JsonElement elem = wrapper.get(string);
		if(elem == null) {
			throw new JsonParseException("no '" + string + "' member found in what was expected to be an interface wrapper");
		}
		System.out.println("passes " + string);
		return elem;
	}

	

}
