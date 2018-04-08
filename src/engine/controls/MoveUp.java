package engine.controls;

import engine.entity.GameEntity;
import engine.movement.Flying;

public class MoveUp extends Action {

	@Override
	public void execute(GameEntity entity){
		if(entity.getMovementType() instanceof Flying) {
			entity.setYAcceleration(entity.getSpeedFactor());
		}
	}
}