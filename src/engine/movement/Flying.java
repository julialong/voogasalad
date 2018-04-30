package engine.movement;

import engine.physics.Kinematics;
import engine.physics.Physics;

/**
 * Movement implementation for entities that fly and are exempt from gravity and
 * friction
 * 
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class Flying implements Movement {
	private Physics physics = new Physics();

	@Override
	public Kinematics update(Kinematics k, double xVelocityLimit, double yVelocityLimit) {
		Kinematics kFinal = physics.applyPhysics(k, false, false);
		if (kFinal.getXVelocity() > xVelocityLimit) {
			kFinal.setXVelocity(xVelocityLimit);
		}
		if (kFinal.getXVelocity() < -xVelocityLimit) {
			kFinal.setXVelocity(-xVelocityLimit);
		}
		if (kFinal.getYVelocity() > yVelocityLimit) {
			kFinal.setYVelocity(yVelocityLimit);
		}
		if (kFinal.getYVelocity() < -yVelocityLimit) {
			kFinal.setYVelocity(-yVelocityLimit);
		}
		return kFinal;
	}
}
