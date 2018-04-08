package engine.controls;
import java.util.HashMap;
import java.util.Map;

import engine.entity.Player;
import javafx.scene.input.KeyCode;
/**
 * Takes keyCodes and turns them into info to make a player move.
 * @author Marcus Oertle and Robert Gitau
 */
 public class Controls {
     private Player player;
     private Map<KeyCode, Action> keyBindings = new HashMap<>();
     
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
         this(player, KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.SPACE, KeyCode.P);
     }
     
     public Controls(Player player, KeyCode up, KeyCode down, KeyCode left, KeyCode right, KeyCode jump, KeyCode attac){
         this.player = player;
         keyBindings.put(up, new MoveUp());
         keyBindings.put(down, new MoveDown());
         keyBindings.put(left, new MoveLeft());
         keyBindings.put(right, new MoveRight());
         keyBindings.put(jump, new Jump());
         keyBindings.put(attac, new Attack());
     }
     
     /**
      * Sets the binding of KeyCode to Action class
      * @param key - the KeyCode
      * @param action - associated instance of action class
      */
     public void setBinding(KeyCode key, Action action){
         keyBindings.put(key, action);
     }
     
     /**
      * Activate the actions associated with a key binding
      * @param key - the KeyCode
      */
     public void activate(KeyCode key) {
    	 if(keyBindings.keySet().contains(key)) {
    		 keyBindings.get(key).execute(player);
    	 }
     }
     
     /**
      * Stops the player from accelerating. Likely needs some work.
      */
     public void deactivate() {
    	 new Stop().execute(player);
     }
 }