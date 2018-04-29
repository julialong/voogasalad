package engine.behavior;

import engine.controls.Action;
import engine.controls.MoveLeft;
import engine.controls.MoveRight;
import engine.entity.GameEntity;
import engine.entity.Player;

/**
 * Behavior implementation that causes entity to move forward
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class MoveForward implements Behavior {
		private Action action;
	    private Player player;
	    
	    public MoveForward(){
	        //this.player = player;
	    }
	    
	    @Override
	    public void update(GameEntity entity) {
	        if(entity.getKinematics().getXVelocity() > 0){
	        	action = new MoveRight();
	        } else {
	        	action = new MoveLeft();
	        }
	    	action.execute(entity);
	    }
		
	}
