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
}
