package engine.behavior;

import engine.controls.Jump;
import engine.entity.GameEntity;

/**
 * Behavior implementation that causes entity to jump as it moves
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class JumpALot implements Behavior{
	//private int counter = 0;
	//private int frequency = 10;

	@Override
	public void update(GameEntity entity) {
		Jump jump = new Jump();
		jump.execute(entity);
	}

}