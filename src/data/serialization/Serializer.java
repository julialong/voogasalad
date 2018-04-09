package data.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import engine.weapon.Weapon;

public class Serializer	{
//	private static Gson gson = new;
	private static Gson gson = new Gson();

	protected String serialize(Object obj)	{
//		GsonBuilder builder = new GsonBuilder();
//		builder.registerTypeAdapter(Weapon.class, new WeaponAdapter());
//		gson = builder.create();
        return gson.toJson(obj);
	}
}