package engine.entity;

import engine.behavior.Behavior;
import engine.weapon.Weapon;
/**
 * Defines entities that generally serve to harm or impede a player.
 * @author Robert Gitau and Marcus Oertle
 *
 */
public abstract class Enemy implements GameEntity {
	/**
	 * Defines how the enemy behaves with regard to the player (ie chases the
	 * player)
	 * 
	 * @param behavior:
	 *            the enemies Behavior type.
	 */
	public abstract void addBehavior(Behavior behavior);

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
