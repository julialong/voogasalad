package data.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import engine.weapon.Weapon;

public class Serializer	{
	private static Gson gson = new Gson();

	protected String serialize(Object obj)	{
        return gson.toJson(obj);
	}
}