package engine.controls;
import java.util.HashMap;
import java.util.Map;

import engine.entity.Player;
import javafx.scene.input.KeyCode;
/**
 * Takes keyCodes and turns them into info to make a player move.
 *
 */
 public class Controls {
     private Player player;
     private Map<KeyCode, Action> keyBindings = new HashMap<>();
     
     public Controls(Player player){
         this(player, KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.SPACE, KeyCode.P);
     }
     public Controls(Player player, KeyCode up, KeyCode down, KeyCode left, KeyCode right, KeyCode jump, KeyCode attac){
         this.player = player;
         keyBindings.put(up, new MoveUp(this.player));
         keyBindings.put(down, new MoveDown(this.player));
         keyBindings.put(left, new MoveLeft(this.player));
         keyBindings.put(right, new MoveRight(this.player));
         keyBindings.put(jump, new Jump(this.player));
         keyBindings.put(attac, new Attack(this.player));
     }
     
     public void setBinding(KeyCode key, Action action){
         keyBindings.put(key, action);
     }
 }