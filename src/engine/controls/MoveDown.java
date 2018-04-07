package engine.controls;

import engine.entity.GameEntity;

public class MoveDown extends Action {

    @Override
    public void execute(GameEntity entity){
        entity.setYAcceleration(-entity.getSpeedFactor());
    }
}