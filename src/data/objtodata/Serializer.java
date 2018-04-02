package data.objtodata;

import java.lang.reflect.Type;
import com.google.gson.Gson;

/**
 * @author Maya Messinger
 * Started 1 Apr 18
 * Class that serializes game objects - converts from an object (from GAE or Engine) and converts to JSON
 */
public class Serializer	{
	private Gson gson = new Gson();

	/**
	 * Serializes object src
	 */
	public String serialize()	{
		ExampleObject src = new ExampleObject(12);

		return gson.toJson(src);
	}

	/**
	 * Serializes object src
	 * @param src	object to serialize
	 */
	public String serialize(ExampleObject src, Type typeOfSrc)	{
		return gson.toJson(src);
	}

	/**
	 * Serializes object obj to be of specific type type
	 * @param obj	object to serialize
	 * @param type	class type to serialize to (specific)
	 */
	public String serialize(Object obj, Type type)	{
		return gson.toJson(obj, type);
	}
}