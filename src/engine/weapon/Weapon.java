package engine.weapon;

import engine.entity.GameEntity;
/**
 * Defines something used by an Enemy or PlayerCharacter to effect harm on a certain GameEntity in collision with the weapon.
 * @author Robert Gitau and Marcus Oertle
 *
 */
public interface Weapon {
	/**
	 * Causes the weapon to attack, damaging a target.
	 */
	public abstract void attack();

	/**
	 * Returns the angle of the weapon
	 * @return angle (double)
	 */
	public abstract double getAngle();

	/**
	 * Updates which direction the player is facing
	 */
	public abstract void updateDirectionality();

	/**
	 * Iterates through all entities and damages the appropriate ones
	 */
	public abstract void iterateEntities();

	/**
	 * Activates the weapon
	 */
	public void activate();
	
	/**
	 * Deactivates the weapon
	 */
	public abstract void deactivate();
	
	/**
	 * Returns the state of the weapon
	 * @return true if active
	 */
	public abstract boolean getActive();
}
