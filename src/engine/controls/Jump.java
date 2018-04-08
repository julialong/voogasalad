package engine.controls;

import engine.entity.GameEntity;

public class Jump extends Action {
    
    @Override
    public void execute(GameEntity entity) {
        entity.setYVelocity(entity.getJumpFactor());
    }
}