package engine.controls;

import engine.entity.GameEntity;

public class MoveUp extends Action {

    @Override
    public void execute(GameEntity entity){
        entity.setYAcceleration(entity.getSpeedFactor());
    }
}