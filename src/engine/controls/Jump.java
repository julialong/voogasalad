package engine.controls;

import engine.entity.GameEntity;
import engine.movement.Flying;
import engine.movement.LinearFlying;

public class Jump extends Action {
    
    @Override
    public void execute(GameEntity entity) {
    	if(entity.getKinematics().getYVelocity() != 0 || entity.getMovementType() instanceof Flying 
    			|| entity.getMovementType() instanceof LinearFlying) return;
        entity.setYVelocity(entity.getJumpFactor());
    }
}