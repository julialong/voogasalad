package api.engine;

/**
 * Interface for weapon type classes that can be utilized by either the player or the enemy classes.
 * @author Marcus
 *
 */
public interface Weapon {
	/**
	 * Sets the attack method type for a weapon (ie. swing, stab, shoot, etc...)
	 * @param method - the string representation of the desired attack method
	 */
	public abstract void setAttackMethod(String method);
	
	/**
	 * Causes the weapon to attack
	 */
	public abstract void attack();
}