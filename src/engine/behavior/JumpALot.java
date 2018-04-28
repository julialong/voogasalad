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
	private Jump jump = new Jump();

	@Override
	public void update(GameEntity entity) {
		jump.execute(entity);
	}

}