package engine.controls;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import engine.controls.resources.Bindings;
import engine.entity.Player;
import javafx.scene.input.KeyCode;
/**
 * Takes keyCodes and turns them into info to make a player move.
 * @author Marcus Oertle and Robert Gitau
 */
public class Controls {
	private Player player;
	private Map<KeyCode, Action> keyBindings = new HashMap<>();
	private ArrayList<KeyCode> pressedKeys = new ArrayList<>();
	private boolean allowJump = true;
	private Bindings bindingsToFile = new Bindings();;

	/**
	 * Default key bindings:<br>
	 * W 		= move up<br>
	 * S 		= move down<br>
	 * A 		= move left<br>
	 * D 		= move right<br>
	 * SPACE 	= jump<br>
	 * P 		= attack<br>
	 * @param player
	 */
	public Controls(Player player){
		this.player = player;
		keyBindings.put(bindingsToFile.getKey("MoveUp"), new MoveUp());
		keyBindings.put(bindingsToFile.getKey("MoveDown"), new MoveDown());
		keyBindings.put(bindingsToFile.getKey("MoveLeft"), new MoveLeft());
		keyBindings.put(bindingsToFile.getKey("MoveRight"), new MoveRight());
		keyBindings.put(bindingsToFile.getKey("Jump"), new Jump());
		keyBindings.put(bindingsToFile.getKey("Attack"), new Attack());
	}

	/**
	 * Sets the binding of KeyCode to Action class
	 * @param key - the KeyCode
	 * @param action - associated instance of action class
	 * @throws IOException 
	 */
	public void setBinding(KeyCode key, Action action) throws IOException{
		keyBindings.remove(key);
		keyBindings.put(key, action);
		bindingsToFile.updatePropertiesFile(key, action);
	}

	/**
	 * Activate the actions associated with a key binding
	 * @param key - the KeyCode
	 */
	public void activate(KeyCode key) {
		System.out.println("Activate: " + key.getName());
		if(!pressedKeys.contains(key)) {
			pressedKeys.add(key);
		}	
		if(keyBindings.keySet().contains(key)) {
			if(keyBindings.get(key) instanceof Jump) {
				if(allowJump) {
					allowJump = false;
					keyBindings.get(key).execute(player);
				}
			}
			else {
				keyBindings.get(key).execute(player);
			}
		}
	}

	/**
	 * Stops the player from accelerating. Likely needs some work.
	 */
	public void deactivate(KeyCode key) {
		System.out.println("Deactivate: " + key.getName());
		pressedKeys.remove(key);
		if(keyBindings.get(key) instanceof Jump) {
			allowJump = true;
		}
		else {
			if(pressedKeys.isEmpty()) {
				new Stop().execute(player);
				System.out.println("Stop");
			}
			else {
				if((keyBindings.get(pressedKeys.get(0)) instanceof Jump)){
					new Stop().execute(player);
					System.out.println("Stop");
				}
				else {
					activate(pressedKeys.get(0));
					System.out.println("activate: " + pressedKeys.get(0).getName());
				}
			}
		}
	}
}