package engine.movement;

import engine.physics.Kinematics;

/**
 * A Movement type that prevents all movement
 * 
 * @author Marcus Oertle
 *
 */
public class Static implements Movement {

	@Override
	public Kinematics update(Kinematics k, double xVelocityLimit, double yVelocityLimit) {
		return k;
	}
}
