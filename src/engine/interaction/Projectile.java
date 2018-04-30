package engine.interaction;

import engine.behavior.Behavior;
import engine.behavior.JumpALot;
import engine.behavior.JumpBetweenPoints;
import engine.entity.GameEntity;
import engine.movement.Grounded;
import engine.physics.DetectCollision;
import engine.weapon.Weapon;

/**
 * Defines the behavior of Entities intended to act like projectiles that
 * dissappear on contact unless they can jump
 * 
 * @author Robert Gitau
 *
 */
public class Projectile implements Interaction {
	private GameEntity owner;
	private int damage;

	public Projectile(GameEntity owner, int damage) {
		this.owner = owner;
		this.damage = damage;
	}

	@Override
	public void interact(GameEntity source, GameEntity target) {
		if ((target.equals(owner)) || (target instanceof Weapon))
			return;
		if (target.getDestructible())
			target.setHealth(target.getHealth() - damage);
		bulletRemoval(source, target);
	}

	/**
	 * Defines logic used to allow bullets that can jump to jump but otherwise
	 * disappear on collision
	 * 
	 * @param source
	 * @param target
	 */
	private void bulletRemoval(GameEntity source, GameEntity target) {
		if (!(source.getMovementType() instanceof Grounded)) {
			source.setHealth(0);
		} else if (!(new DetectCollision().detect(source, target).equals("bottom"))) {
			source.setHealth(0);
		}
		boolean jumper = false;
		for (Behavior b : source.getBehaviorList()) {
			if ((b instanceof JumpALot) || (b instanceof JumpBetweenPoints)) {
				jumper = true;
				break;
			}
		}
		if (!jumper)
			source.setHealth(0);
	}

	/**
	 * Sets the owner of the Projectile, ie a target that will never get hurt by
	 * the projectile
	 * 
	 * @param owner
	 *            the GameEntity this projectile was fired by
	 */
	public void setOwner(GameEntity owner) {
		this.owner = owner;
	}

	/**
	 * Sets the damage dealt by the Projectile
	 * 
	 * @param damage
	 *            the damage dealt on contact
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
}
