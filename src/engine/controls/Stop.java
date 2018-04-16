package engine.controls;

import engine.entity.GameEntity;
import engine.movement.LinearFlying;
import engine.movement.LinearGrounded;

public class Stop extends Action {

    @Override
    public void execute(GameEntity entity){
    	if(entity.getMovementType() instanceof LinearGrounded || entity.getMovementType() instanceof LinearFlying) {
    		entity.setXVelocity(0);
    	}
    	else {
    		entity.setXAcceleration(0);
    	}
    }
}