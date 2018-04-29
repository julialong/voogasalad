package engine.behavior;

import engine.controls.Action;
import engine.controls.MoveLeft;
import engine.entity.GameEntity;
import engine.entity.Player;

/**
 * Behavior implementation that causes entity to move forward
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class MoveForward implements Behavior {
		private Action action = new MoveLeft();
	    private Player player;
	    
	    public MoveForward() {
		}
	    
	    public MoveForward(Player player){
	        this.player = player;
	    }
	   
		@Override
	    public void update(GameEntity entity) {
	        action.execute(entity);
	    }
		
	}
