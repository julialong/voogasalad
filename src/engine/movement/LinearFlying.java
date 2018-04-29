package engine.movement;

import engine.physics.Kinematics;

/**
 * Movement implementation for entities that fly and are exempt from gravity and
 * friction and move with constant velocity
 * 
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class LinearFlying implements Movement {
	private Flying flying = new Flying();

	@Override
	public Kinematics update(Kinematics k, double xVelocityLimit, double yVelocityLimit) {
		return flying.update(k, xVelocityLimit, yVelocityLimit);
	}
}
