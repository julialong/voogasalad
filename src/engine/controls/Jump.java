package engine.controls;

import engine.entity.GameEntity;

public class Jump extends Action {
    
    @Override
    public void execute(GameEntity entity) {
    	if(entity.getKinematics().getYVelocity() != 0) return;
        entity.setYVelocity(entity.getJumpFactor());
    }
}