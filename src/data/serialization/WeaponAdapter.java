package data.serialization;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import engine.weapon.Weapon;

public class WeaponAdapter implements JsonSerializer<Weapon>, JsonDeserializer<Weapon> {
	
	private static final String CLASSNAME= "ClassName";
	private static final String INSTANCE = "Instance";

	@Override
	public JsonElement serialize(Weapon arg0, Type arg1, JsonSerializationContext arg2) {
		JsonObject returnValue = new JsonObject();
		String className = arg0.getClass().getName();
		returnValue.addProperty(CLASSNAME, className);
		JsonElement element = arg2.serialize(arg0);
		returnValue.add(INSTANCE, element);
		// TODO Auto-generated method stub
		return returnValue;
	}
	
	@Override
	public Weapon deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject weapon = arg0.getAsJsonObject();
		JsonPrimitive primitive = (JsonPrimitive) weapon.get(CLASSNAME);
		String className = primitive.getAsString();
		
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			//thown new JsonParserException(e.getMessage());
		}
		
		// TODO Auto-generated method stub
		return arg2.deserialize(weapon.get(INSTANCE), clazz);
	}

	

}
