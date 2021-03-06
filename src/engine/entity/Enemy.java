package engine.entity;

import engine.behavior.NoBehavior;
import engine.movement.Grounded;
import engine.physics.Kinematics;
import engine.weapon.Weapon;

/**
 * Defines information for basic enemies in a game.
 * 
 * @author Rob
 *
 */
public class Enemy extends GameObject {
	private Weapon weaponType;

	public Enemy() {
		this(0, 0);
	}

	public Enemy(double x, double y) {
		kinematics = new Kinematics(x, y, 0, 0, 0, 0);
		movementType = new Grounded();
		behaviorList.add(new NoBehavior());
		speedFactor = 500; // arbitrary for now
		jumpFactor = 150; // arbitrary for now
		maxVelocityX = 30; // arbitrary for now
		maxVelocityY = 500; // arbitrary for now
		destructible = true;
	}

	/**
	 * Sets the enemy's weapon, which implements the Weapon interface.
	 * 
	 * @param weapon:
	 *            the Weapon the enemy is given
	 */
	public void setWeapon(Weapon weapon) {
		weaponType = weapon;
		weaponType.activate();
	}

	/**
	 * Gets the weapon the enemy holds
	 * 
	 * @return weaponType - weapon the enemy holds
	 */
	public Weapon getWeapon() {
		return weaponType;
	}

	/**
	 * Performs whatever effect the enemy's current weapon has in its attack
	 * method.
	 */
	public void useWeapon() {
		if (weaponType != null) {
			weaponType.attack();
		}
	}
}