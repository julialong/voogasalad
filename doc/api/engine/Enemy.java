/**
 * Defines the logic for enemies, moving entities that usually deal damage or
 * otherwise actively obstruct the player, and how they interact with the game
 * world.
 */
public interface Enemy {
	/**
	 * Defines how the enemy behaves with regard to the player (ie chases the
	 * player)
	 * 
	 * @param behavior:
	 *            the enemies Behavior type.
	 */
	public abstract void setBehavior(Behavior behavior);

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
}