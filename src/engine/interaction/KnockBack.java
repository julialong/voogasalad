package engine.interaction;

import engine.entity.GameEntity;
import engine.physics.Kinematics;

/**
 * The source causes the target to get knocked away from the source
 * @author Robert Gitau
 *
 */
public class KnockBack implements Interaction{

	@Override
	public void interact(GameEntity source, GameEntity target) {
		Kinematics k = target.getKinematics();
		target.setXVelocity(-k.getXVelocity());
	}

}
