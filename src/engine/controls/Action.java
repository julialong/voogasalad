package engine.controls;

import engine.entity.GameEntity;

public abstract class Action {
    public abstract void execute(GameEntity entity);
}