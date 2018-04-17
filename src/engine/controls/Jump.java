package engine.controls;

import engine.entity.GameEntity;

public class Jump extends Action {
    
    @Override
    public void execute(GameEntity entity) {
    	if(entity.getKinematics().getYVelocity() != 0) return; // temporary jump limit to 1
        entity.setYVelocity(entity.getJumpFactor());
    }
}