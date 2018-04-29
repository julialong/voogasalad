package engine.interaction;

import engine.controls.Jump;
import engine.entity.GameEntity;
import engine.movement.Static;
import engine.physics.DetectCollision;

/**
 * Interaction implementation that causes an entity to attempt to jump over obstacles it runs into.
 * @author Robert Gitau
 *
 */
public class JumpOverObstacle implements Interaction{

	@Override
	public void interact(GameEntity source, GameEntity target) {
		if(target.getMovementType() instanceof Static) {
			return;
		}
		String collisionType = new DetectCollision().detect(source, target);
		if((collisionType.equals("left")) || (collisionType.equals("right"))){
			Jump jump = new Jump();
			jump.execute(source);
		}
	}

}
