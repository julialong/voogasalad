package engine.interaction;

import engine.entity.GameEntity;
import engine.physics.DetectCollision;
import engine.physics.Kinematics;

/**
 * The source causes the target to get knocked away from the source
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class KnockBack implements Interaction{

	@Override
	public void interact(GameEntity source, GameEntity target) {
		String collisionType = new DetectCollision().detect(source, target);
		
		if(!(collisionType.equals("top") || collisionType.equals("bottom"))) {
			Kinematics k = target.getKinematics();
			target.setXVelocity(-30*k.getXVelocity());
		}
	}

}
