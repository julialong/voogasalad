package engine.controls;

import engine.entity.GameEntity;

public class MoveLeft extends Action {

    @Override
    public void execute(GameEntity entity){
        entity.setXAcceleration(-entity.getSpeedFactor());
    }
}