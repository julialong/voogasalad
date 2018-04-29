package engine.interaction;

import engine.entity.GameEntity;

/**
 * The source harms the target.
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class HarmTarget implements Interaction{
	private int damage;
	
	public HarmTarget(){
		this(1);
	}
	
	public HarmTarget(int damage){
		this.damage = damage;
	}

	@Override
	public void interact(GameEntity source, GameEntity target) {
		//System.out.println("dealing damage to " + target.getClass().getSimpleName() + " in amount of " + damage);
		target.setHealth(target.getHealth()-damage);
	}

}
