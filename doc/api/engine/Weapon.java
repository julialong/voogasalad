package api.engine;

/**
 * Interface for weapon type classes that can be utilized by either the player or the enemy classes.
 * @author Marcus
 *
 */
public interface Weapon {	
	/**
	 * Causes the weapon to attack
	 */
	public abstract void attack(GameEntity entity);
}