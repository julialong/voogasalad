package api.engine;

/**
 * Defines the logic for how the character controlled by the Player moves and
 * interacts with the game-world around it.
 */
public interface PlayerCharacter {
	/**
	 * Used to move the player character, given an intitial velocity, using
	 * the internal physics engine.
	 * 
	 * @param velocity:
	 *            the intitial the object should move in the x direction
	 */
	public abstract void moveX(double velocity);
	
	/**
	 * Used to move the player character, given an intitial velocity, using
	 * the internal physics engine.
	 * 
	 * @param velocity:
	 *            the intitial the object should move in the y direction
	 */
	public abstract void moveY(double velocity);

	/**
	 * Defines how the player moves, namely the player's base speed and weight.
	 * 
	 * @param movement:
	 *            the player's movement type, an implementation of the Movement
	 *            interface.
	 */
	public abstract void setMovementType(Movement movement);

	/**
	 * Sets the player's current health, which ends the game/ loses a life if it
	 * reaches 0.
	 * 
	 * @param HP:
	 *            an int representing what the HP value should be set to.
	 */
	public abstract void setHealth(int HP);

	/**
	 * Sets the player's weapon, which implements the Weapon interface.
	 * 
	 * @param weapon:
	 *            the Weapon the player is given
	 */
	public abstract void setWeapon(Weapon weapon);

	/**
	 * performs whatever effect the player's current weapon has in its attack
	 * method.
	 */
	public abstract void useWeapon();

	/**
	 * Defines how the player character reacts to the Object given, if the
	 * object is an Enemy, GameObject, PowerUp, or Projection.
	 * 
	 * @param o:
	 *            an Object to be interacted with.
	 */
	public abstract void setInteraction(Object o);

	/**
	 * Adds a power up to the Player, which has behavoir defined internally.
	 * 
	 * @param power:
	 *            the PowerUp to be added.
	 */
	public abstract void addPowerUp(PowerUp power);

	/**
	 * Removes an added power up from the player, preventing its effects.
	 * 
	 * @param power:
	 *            the PowerUp to be removed.
	 */
	public abstract void removePowerUp(PowerUp power);
}