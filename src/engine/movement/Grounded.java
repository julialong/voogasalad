package engine.movement;

import engine.physics.Kinematics;
import engine.physics.Physics;

/**
 * Movement implementation for entities that remain on the ground and are
 * affected by gravity
 * 
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class Grounded implements Movement {
	private Physics physics = new Physics();

	@Override
	public Kinematics update(Kinematics k, double xVelocityLimit, double yVelocityLimit) {
		Kinematics kFinal = physics.applyPhysics(k, true, true);
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