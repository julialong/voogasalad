package api.engine;

/**
 * Defines the logic for enemies, moving entities that usually deal damage or
 * otherwise actively obstruct the player, and how they interact with the game
 * world.
 */
public interface Enemy {
	/**
	 * Used to move the enemy character, given a distance and a heading, using
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
	 * Defines how the enemy moves, namely the enemy's base speed and weight.
	 * 
	 * @param movement:
	 *            the enemy's movement type, an implementation of the Movement
	 *            interface.
	 */
	public abstract void setMovementType(Movement movement);

	/**
	 * Defines how the enemy behaves with regard to the player (ie chases the
	 * player)
	 * 
	 * @param behavior:
	 *            the enemies Behavior type.
	 */
	public abstract void setBehavior(Behavior behavior);

	/**
	 * Sets the enemy's current health.
	 * 
	 * @param HP:
	 *            an int representing what the HP value should be set to.
	 */
	public abstract void setHealth(int HP);

	/**
	 * Sets the enemy's weapon, which implements the Weapon interface.
	 * 
	 * @param weapon:
	 *            the Weapon the enemy is given
	 */
	public abstract void setWeapon(Weapon weapon);

	/**
	 * performs whatever effect the enemy's current weapon has in its attack
	 * method.
	 */
	public abstract void useWeapon();

	/**
	 * Defines how the enemy character reacts to the Object given, if the object
	 * is an Enemy, GameObject, PowerUp, or Projection.
	 * 
	 * @param o:
	 *            an Object to be interacted with.
	 */
	public abstract void setInteraction(Object o);
}