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

/**
 * This class manages the deserialization of behaviors that require a player input to function.
 * 
 * @author Belanie Nagiel
 *
 */
public class BehaviorSkipManager {
	
	private static final String BEHAVIOR_SKIPS = "data.resources/behaviorsToSkip";
	private List<String> behaviorsToSkip;
	
	/**
	 * Class Constructor
	 * 
	 * Creates the list of behaviors to skip.
	 */
	public BehaviorSkipManager(){
		behaviorsToSkip = new ArrayList<String>();
		behaviorsToSkip = buildBehaviorSkipMap();
	}
	
	/**
	 * Reads the properties file that specifies which behaviors need to be addressed differently
	 * in deserialization in order to add players back into them.
	 * 
	 * @return
	 */
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
	
	/**
	 * Returns an instance of the behavior with the correct player added to it.
	 * 
	 * @param behaviorType
	 * @param player
	 * @return
	 */
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
	
	/**
	 * Returns a version of the behavior with no player for original deserialization.
	 * 
	 * @param behaviorType
	 * @return
	 */
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

	/**
	 * Getter for the list of behaviors to skip.
	 * @return
	 */
	public List<String> getBehaviorsToSkip() 
	{
		return behaviorsToSkip;
	}	
}
