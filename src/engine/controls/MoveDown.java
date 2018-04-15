package engine.controls;

import engine.entity.GameEntity;
import engine.movement.Flying;
import engine.movement.LinearFlying;

public class MoveDown extends Action {

    @Override
    public void execute(GameEntity entity){
		if(entity.getMovementType() instanceof Flying) {
			 entity.setYAcceleration(-entity.getSpeedFactor());
		}
    	if(entity.getMovementType() instanceof LinearFlying) {
    		entity.setYVelocity(entity.getMaxYVelocity());
    	}
    }
}