package engine.controls;

import engine.entity.GameEntity;
import engine.movement.LinearFlying;
import engine.movement.LinearGrounded;

public class MoveLeft extends Action {

    @Override
    public void execute(GameEntity entity){
    	if(entity.getMovementType() instanceof LinearGrounded || entity.getMovementType() instanceof LinearFlying) {
    		entity.setXVelocity(-entity.getMaxXVelocity());
    	}
    	else {
    		entity.setXAcceleration(-entity.getSpeedFactor());
    	}
    }
}