package data.serialization;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.JsonParseException;

import engine.behavior.Behavior;
import engine.entity.Player;

public class BehaviorSkipManager {
	
	private static final String BEHAVIOR_SKIPS = "data.resources/behaviorsToSkip";
	private List<String> behaviorsToSkip;
	
	public BehaviorSkipManager(){
		behaviorsToSkip = new ArrayList<String>();
		behaviorsToSkip = buildBehaviorSkipMap();
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
	
	public Behavior getBehavior(String behaviorType, Player player) {
		try 
		{
			Class behaviorClass = Class.forName(behaviorType);
			Constructor<?> c = behaviorClass.getConstructor(Player.class);
			c.setAccessible(true);
			Object o = c.newInstance(player);
			System.out.println(o);
			return (Behavior)o;
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) 
		{
			throw new JsonParseException("Could not create behavior that contains a player");
		}
		
	}
	
	public Behavior getEmptyBehavior(String behaviorType) {
		try 
		{
			Class behaviorClass = Class.forName(behaviorType);
			Constructor<?> c = behaviorClass.getConstructor();
			c.setAccessible(true);
			Object o = c.newInstance();
			System.out.println(o);
			return (Behavior)o;
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) 
		{
			throw new JsonParseException("Could not create behavior that contains a player");
		}
		
	}

	public List<String> getBehaviorsToSkip() 
	{
		return behaviorsToSkip;
	}	
}
