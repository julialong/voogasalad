/**
 * Defines GameObjects, objects that players and/or enemies can interact with, but usually don't actively try to hurt the player
 */
public interface GameObject {
	/**
	 * Used to move the GameObject character, given a distance and a heading, using
	 * the internal physics engine.
	 * 
	 * @param distance:
	 *            the distance the object should move
	 * @param heading:
	 *            the direction, in degrees, the object moves in. 0 degrees is
	 *            straight up.
	 */
	public abstract void move(double distance, double heading);

	/**
	 * Defines how the GameObject moves, namely the GameObject's base speed and weight.
	 * 
	 * @param movement:
	 *            the GameObject's movement type, an implementation of the Movement
	 *            interface.
	 */
	public abstract void setMovementType(Movement movement);

	/**
	 * Defines how the GameObject behaves with regard to the player (ie makes the player slide across it)
	 * 
	 * @param behavior:
	 *            the enemies Behavior type.
	 */
	public abstract void setBehavior(Behavior behavior);

	/**
	 * Sets the GameObject's current health.
	 * 
	 * @param HP:
	 *            an int representing what the HP value should be set to.
	 */
	public abstract void setHealth(int HP);

	/**
	 * Defines how the GameObject character reacts to the Object given, if the object
	 * is an Enemy, GameObject, PowerUp, or Projection.
	 * 
	 * @param o:
	 *            an Object to be interacted with.
	 */
	public abstract void setInteraction(Object o);
}