package engine.interaction;

import engine.entity.GameEntity;
import engine.entity.Player;
import engine.physics.DetectCollision;

/**
 * Damages the enemy when stomped on by the player
 * Should be inherited by the enemy
 * @author Marcus Oertle
 *
 */
public class DamageOnStomp implements Interaction{
	private int damage;
	
	/**
	 * Default constructor for damage on stomp, sets damage to 1.
	 */
	public DamageOnStomp(){
		this(1);
	}
	
	/**
	 * Constructor for setting the damage done on a stomp.
	 * @param damage
	 */
	public DamageOnStomp(int damage){
		this.damage = damage;
	}

	@Override
	public void interact(GameEntity source, GameEntity target) {
		if(!(target instanceof Player)) return;
		if(target.getKinematics().getYVelocity() > 0) return;

		String collisionType = new DetectCollision().detect(source, target);

		if (collisionType.equals("top"))
		{
			source.setHealth(source.getHealth() - damage);
			target.setYVelocity(target.getJumpFactor());
		}
	}
}
