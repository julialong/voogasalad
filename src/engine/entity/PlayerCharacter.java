package engine.entity;

import java.util.List;

import engine.powerup.PowerUp;
import engine.weapon.Weapon;

/**
 * Abstract Class defining behavior specific to entities that generally move based on input.
 * @author Robert Gitau and Marcus Oertle
 *
 */
public abstract class PlayerCharacter extends GameObject{
	
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
	 * Adds a power up to the Player, which has behavior defined internally.
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
	
	/**
	 * Sets the power ups list
	 * @param powerUpsList
	 */
	public abstract void setPowerUpsList(List powerUpsList);
	
	/**
	 * Returns the power up list
	 */
	public abstract List getPowerUpsList();
}
