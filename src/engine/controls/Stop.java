package engine.controls;

import engine.entity.GameEntity;
import engine.movement.Flying;
import engine.movement.LinearFlying;
import engine.movement.LinearGrounded;

public class Stop extends Action {

    @Override
    public void execute(GameEntity entity){
    	if(entity.getMovementType() instanceof LinearGrounded) {
    		entity.setXVelocity(0);
    	}
    	else if(entity.getMovementType() instanceof LinearFlying || entity.getMovementType() instanceof Flying){
    		entity.setXVelocity(0);
    		entity.setYVelocity(0);
    		entity.setXAcceleration(0);
    		entity.setYAcceleration(0);
    	}
    	else {
    		entity.setXAcceleration(0);
    	}
    }
}