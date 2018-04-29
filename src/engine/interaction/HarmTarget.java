package engine.interaction;

import engine.entity.GameEntity;

/**
 * The source harms the target.
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class HarmTarget implements Interaction{
	private int damage;
	
	/**
	 * Default constructor, damage defaulted to 1.
	 */
	public HarmTarget(){
		this(1);
	}
	
	/**
	 * Constructor that defines damage for the interaction.
	 * @param damage
	 */
	public HarmTarget(int damage){
		this.damage = damage;
	}

	@Override
	public void interact(GameEntity source, GameEntity target) {
		//System.out.println("dealing damage to " + target.getClass().getSimpleName() + " in amount of " + damage);
		target.setHealth(target.getHealth()-damage);
	}

}
