package serialization;

import com.google.gson.Gson;

public class Serializer	{
	private static Gson gson = new Gson();

	protected String serialize(Object obj)	{
        return gson.toJson(obj);
	}
}