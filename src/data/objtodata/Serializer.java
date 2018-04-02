package data.objtodata;

import com.google.gson.Gson;

public class Serializer	{
	private static Gson gson = new Gson();

	protected void serialize(Object obj)	{
        System.out.println(gson.toJson(obj));
	}
}