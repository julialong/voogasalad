package data.serialization;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class InterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {

	@Override
	public JsonElement serialize(T arg0, Type arg1, JsonSerializationContext arg2) {
		JsonObject wrapper = new JsonObject();
		wrapper.addProperty("type", arg0.getClass().getName());
		wrapper.add("data", new Gson().toJsonTree(arg0));
		return wrapper;
	}
	
	@Override
	public T deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject wrapper = (JsonObject) arg0;
		JsonElement typeName = get(wrapper, "type");
		JsonElement data = get(wrapper, "data");
		Type actualType = typeForName(typeName);
		return arg2.deserialize(data, actualType);
	}

	private Type typeForName(JsonElement typeName) {
		try {
            return Class.forName(typeName.getAsString());
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
	}

	private JsonElement get(JsonObject wrapper, String string) {
		JsonElement elem = wrapper.get(string);
		if(elem == null) {
			throw new JsonParseException("no '" + string + "' member found in what was expected to be an interface wrapper");
		}
		return elem;
	}

	

}
