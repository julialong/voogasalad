package engine.interaction;

import engine.entity.GameEntity;

/**
 * Both Objects in contact damage each other
 * @author Marcus Oertle and Robert Gitau
 *
 */
public class HarmEachOther implements Interaction{
	private int damageSource;
	private int damageTarget;
	
	/**
	 * Default constructor, damage defaulted to 1.
	 */
	public HarmEachOther(){
		this(1,1);
	}
	
	/**
	 * Constructor that define damage to the source and target for this interaction.
	 * @param damageSource
	 * @param damageTarget
	 */
	public HarmEachOther(int damageSource, int damageTarget){
		this.damageSource = damageSource;
		this.damageTarget = damageTarget;
	}

	@Override
	public void interact(GameEntity source, GameEntity target) {
		source.setHealth(source.getHealth()-damageSource);
		target.setHealth(target.getHealth()-damageTarget);
	}

}
