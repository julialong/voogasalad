package engine.movement;

import engine.physics.Kinematics;

/**
 * Movement interface is implemented by all movement types to control how an
 * entity moves.
 * 
 * @author Robert Gitau and Marcus Oertle
 */
public interface Movement {
	/**
	 * Overrides the position within the given Kinematics object
	 * 
	 * @param k
	 *            a Kinematics object to be overridden
	 * @param x
	 *            the desired x position
	 * @param y
	 *            the desired y position
	 * @return the overridden Kinematics
	 */
	public abstract Kinematics overridePosition(Kinematics k, double x, double y);

	/**
	 * Overrides the x velocity within the given Kinematics object
	 * 
	 * @param k
	 *            a Kinematics object to be overridden
	 * @param velocity
	 *            the desired x velocity
	 * @return the overridden Kinematics
	 */
	public abstract Kinematics setVelocityX(Kinematics k, double velocity);

	/**
	 * Overrides the y velocity within the given Kinematics object
	 * 
	 * @param k
	 *            a Kinematics object to be overridden
	 * @param velocity
	 *            the desired y velocity
	 * @return the overridden Kinematics
	 */
	public abstract Kinematics setVelocityY(Kinematics k, double velocity);

	/**
	 * Overrides the x acceleration within the given Kinematics object.
	 * 
	 * @param k
	 *            a Kinematics object to be overridden
	 * @param accel
	 *            the desired x acceleration
	 * @return the overridden Kinematics
	 */
	public abstract Kinematics setAccelerationX(Kinematics k, double accel);

	/**
	 * Overrides the x acceleration within the given Kinematics object.
	 * 
	 * @param k
	 *            a Kinematics object to be overridden
	 * @param accel
	 *            the desired x acceleration
	 * @return the overridden Kinematics
	 */
	public abstract Kinematics setAccelerationY(Kinematics k, double accel);

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