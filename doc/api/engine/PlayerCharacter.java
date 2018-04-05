/**
 * Defines the logic for how the character controlled by the Player moves and
 * interacts with the game-world around it.
 */
public abstract class PlayerCharacter {
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