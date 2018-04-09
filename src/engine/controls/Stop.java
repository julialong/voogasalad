package engine.controls;

import engine.entity.GameEntity;

public class Stop extends Action {

    @Override
    public void execute(GameEntity entity){
        entity.setXAcceleration(0);
    }
}