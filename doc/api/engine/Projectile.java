/**
 * Interface for projectile type classes that can be utilized by projectile weapons
 * @author Marcus
 *
 */
public interface Projectile {
	/**
	 * Moves the projectile a designated distance at a designated heading
	 * @param distance - the distance to move
	 * @param heading - the heading in degrees from 0 degrees which is directly upwards
	 */
	public abstract void move(double distance, double heading);
	
	/**
	 * Sets the movement pattern of the projectile
	 * @param movementType - the string representation of the desired movement type
	 */
	public abstract void setMovementType(String movementType);
	
	/**
	 * Sets the object that the Projectile is interacting with
	 * @param object - the object that the Projectile is interacting with
	 */
	public abstract void setInteraction(Object object);
}