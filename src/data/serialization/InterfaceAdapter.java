package data.serialization;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

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
		BehaviorSkipManager skippedBehaviors = new BehaviorSkipManager();
		List<String> behaviorsToSkip = skippedBehaviors.getBehaviorsToSkip();
		JsonObject wrapper = (JsonObject) arg0;
		JsonElement typeName = get(wrapper, "type");
		if(behaviorsToSkip.contains(typeName.getAsString()))
		{
			return (T)skippedBehaviors.getEmptyBehavior(typeName.getAsString());
		}
		JsonElement data = get(wrapper, "data");
		Type actualType = typeForName(typeName);
		return arg2.deserialize(data, actualType);
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
		JsonElement elem = wrapper.get(string);
		if(elem == null) {
			throw new JsonParseException("no '" + string + "' member found in what was expected to be an interface wrapper");
		}
		return elem;
	}

	

}
