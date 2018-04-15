package engine.movement;

import engine.entity.GameEntity;
import engine.physics.Kinematics;

/**
 * Movement interface is implemented by all movement types to control how an
 * entity moves.
 * 
 * @author Robert Gitau and Marcus Oertle
 */
public interface Movement {
	/**
	 * Updates the given Kinematics based upon pre-defined velocities and
	 * accelerations as well as calculations based on physics.
	 * 
	 * @param k
	 *            the Kinematics to be updated
	 * @param xVelocityLimit
	 *            the highest speed in the x direction an entity can move.
	 * @param yVelocityLimit
	 *            the highest speed in the y direction an entity can move
	 * @return the updated Kinematics
	 */
	public abstract Kinematics update(Kinematics k, double xVelocityLimit, double yVelocityLimit);
}