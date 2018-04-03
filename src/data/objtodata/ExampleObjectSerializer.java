package data.objtodata;

import java.lang.reflect.Type;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSerializationContext;

/**
 * @author Maya Messinger
 * Started 1 Apr 18
 * Class that serializes game objects - converts from an object (from GAE or Engine) and converts to JSON
 */
public class ExampleObjectSerializer implements JsonSerializer<ExampleObject> {

	public JsonElement serialize(ExampleObject src, Type typeOfSrc, JsonSerializationContext context) {
		// return new JsonPrimitive(src);
		return null;
	}
}